package com.ubtrobot.callingsystemservices.navigation;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivityNavigationBinding;
import com.ubtrobot.navigation.Location;
import com.ubtrobot.navigation.Marker;
import com.ubtrobot.navigation.NavMap;
import com.ubtrobot.navigation.NavigationException;
import com.ubtrobot.navigation.NavigationManagerCompat;
import com.ubtrobot.navigation.NavigationProgress;
import com.ubtrobot.skill.Directive;
import com.ubtrobot.skill.SkillManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NavigationActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Navigation";

    private ActivityNavigationBinding mBinding;
    private NavigationManagerCompat mNavigationManager;
    private ProgressivePromise<Void, NavigationException, NavigationProgress> mNavigationPromise;

    private Spinner mSpMarkers;
    private ArrayAdapter<String> mMarkerAdapter;
    private ArrayAdapter<String> mMapAdapter;

    private ArrayList<String> mMapNameList = new ArrayList<>();
    private ArrayList<String> mMarkerNameList = new ArrayList<>();
    private LinkedList<NavMap> mMapList = new LinkedList<>();
    private LinkedList<Marker> mMarkerList = new LinkedList<>();

    private NavMap mCurrMap;
    private Marker mCurrMarker;
    private SkillManager mSkillManager;

    private Param mParam;

    public class Param {
        public ObservableField<String> currMapName = new ObservableField<>();
        public ObservableField<String> isLocated = new ObservableField<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        mParam = new Param();
        mBinding.setParams(mParam);
        initLoggerView();

        ListView mapListView = findViewById(R.id.lv_map);
        mMapAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMapNameList);
        mapListView.setAdapter(mMapAdapter);

        mSpMarkers = findViewById(R.id.sp_markers);
        mMarkerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mMarkerNameList);
        mSpMarkers.setAdapter(mMarkerAdapter);
        mSpMarkers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mMarkerList.size() > position) {
                    mCurrMarker = mMarkerList.get(position);
                    addLog(TAG, "Selected: " + mCurrMarker.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCurrMarker = null;
            }
        });

        mNavigationManager = new NavigationManagerCompat(Robot.globalContext());
        mSkillManager = Robot.globalContext().getSystemService(SkillManager.SERVICE);
    }

    public void getMapList(View view) {
        addLog(TAG, "Call getMapList:");
        mNavigationManager.getNavMapList()
                .done(navMaps -> {
                    addLog(TAG, "Call getMapList: done:" + navMaps.size());
                    handleMapList(navMaps);
                })
                .fail(e -> {
                    addLog(TAG, "Call getMapList: fail:" + e.getMessage());
                    handleMapList(null);
                });
    }

    private void handleMapList(List<NavMap> navMaps) {
        mMapList.clear();
        mMapNameList.clear();

        if (null == navMaps || navMaps.isEmpty()) {
            toast("Failed To Load Map List！");
        } else {
            mMapList.addAll(navMaps);
            for (NavMap navMap : navMaps) {
                mMapNameList.add(navMap.getName());
            }
        }

        runOnUiThread(() -> mMapAdapter.notifyDataSetChanged());
    }

    public void gotoSetMap(View view) {
        addLog(TAG, "Call gotoMap: ");
        mSkillManager.dispatchDirective(Directive.Builder.fromAction(Directive.SOURCE_INTER_PROCESS,
                "/navigation/skill/open/map").build())
                .done(aVoid -> addLog(TAG, "Call gotoMap: done"))
                .fail(e -> addLog(TAG, "Call gotoMap: fail:" + e.getMessage()));
    }

    public void getCurrentMap(View view) {
        addLog(TAG, "Call getCurrentMap: ");
        mNavigationManager.getCurrentNavMap()
                .done(navMap -> {
                    addLog(TAG, "Call getCurrentMap: done:" + navMap.getName());
                    mParam.currMapName.set(navMap.getName());
                    mCurrMap = navMap;
                    handleMarkerList(navMap);
                })
                .fail(e -> {
                    addLog(TAG, "Call getCurrentMap: fail:" + e.getMessage());
                    mParam.currMapName.set("");
                    mCurrMap = null;
                    handleMarkerList(null);
                });
    }

    private void handleMarkerList(NavMap navMap) {
        mMarkerList.clear();
        mMarkerNameList.clear();

        if (null != navMap) {
            mMarkerList.addAll(navMap.getMarkerList());
            if (mMarkerList.size() > 0) {
                for (Marker marker : mMarkerList) {
                    mMarkerNameList.add(marker.getTitle());
                }

                mCurrMarker = mMarkerList.get(0);
                mSpMarkers.setSelection(0);
            }
        }

        mMarkerAdapter.notifyDataSetChanged();
    }

    public void gotoLocate(View view) {
        addLog(TAG, "Call gotoLocating: ");
        mSkillManager.dispatchDirective(Directive.Builder.fromAction(Directive.SOURCE_INTER_PROCESS,
                "/location/skill/location/start").build())
                .done(aVoid -> addLog(TAG, "Call gotoLocating: done"))
                .fail(e -> addLog(TAG, "Call gotoLocating: fail:" + e.getMessage()));
    }

    public void getLocateStatus(View view) {
        mHandler.post(() -> {
            boolean isLocated = mNavigationManager.isLocatingSelf();
            addLog(TAG, "Call getLocateStatus: " + isLocated);
            runOnUiThread(() -> mParam.isLocated.set(isLocated + ""));
        });
    }

    public void startNavigate(View view) {
        if (mCurrMap == null || mCurrMarker == null) {
            addLog(TAG, "Field mCurrMap or mCurrMarker is null.");
            return;
        }

        Location location = new Location.Builder(mCurrMarker.getPosition())
                .setRotation(mCurrMarker.getRotation())
                .setZ(mCurrMarker.getZ())
                .build();
        navigate(location);
    }

    public void navigate(Location destination) {
        if (mNavigationPromise != null) {
            mNavigationPromise.cancel();
        }

        addLog(TAG, "Call navigate: destination: " + destination.toString());
        mNavigationPromise = mNavigationManager.navigate(destination).progress((NavigationProgress navigationProgress) -> {
            addLog(TAG, "Call navigate: progress: " + navigationProgress.getLocation().toString());
        }).done((Void aVoid) -> {
            mNavigationPromise = null;
            addLog(TAG, "Call navigate: done");
        }).fail((NavigationException e) -> {
            mNavigationPromise = null;
            addLog(TAG, "Call navigate: fail: " + e.getMessage());
        });
    }

    public void stopNavigate(View view) {
        addLog(TAG, "Call stopNavigate.");
        if (mNavigationPromise != null) {
            mNavigationPromise.cancel();
        }
    }

    private void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
