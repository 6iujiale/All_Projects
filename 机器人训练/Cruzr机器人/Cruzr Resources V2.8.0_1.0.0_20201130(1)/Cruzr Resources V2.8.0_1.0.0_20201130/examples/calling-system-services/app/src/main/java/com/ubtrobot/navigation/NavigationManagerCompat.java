package com.ubtrobot.navigation;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.ubtrobot.async.CancelledCallback;
import com.ubtrobot.async.Consumer;
import com.ubtrobot.async.Function;
import com.ubtrobot.async.ListenerList;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.async.ProgressivePromiseOperators;
import com.ubtrobot.async.Promise;
import com.ubtrobot.async.PromiseOperators;
import com.ubtrobot.context.RobotContext;
import com.ubtrobot.rosa.Build;

import java.util.LinkedList;
import java.util.List;

/**
 * All docs see {@link NavigationManager}
 */
public class NavigationManagerCompat implements Navigator, NavMapProvider {
    private RobotContext mContext;
    private NavigationManager mManager;

    private boolean mNeedCompat = TextUtils.equals(Build.VERSION, "2.7.0");

    private final ListenerList<LocationListener> mListeners = new ListenerList<>(
            new Handler(Looper.getMainLooper()));

    public NavigationManagerCompat(RobotContext context) {
        this.mContext = context;

        mManager = mContext.getSystemService(NavigationManager.SERVICE);
        mManager.registerListener(mLocationListener);
    }

    @Override
    public Promise<List<NavMap>, NavMapException> getNavMapList() {
        if (!mNeedCompat) {
            return this.mManager.getNavMapList();
        }

        Promise<List<NavMap>, NavMapException> promise = this.mManager.getNavMapList();

        return PromiseOperators.mapDone(promise,
                new Function<List<NavMap>, List<NavMap>, NavMapException>() {
                    @Override
                    public List<NavMap> apply(List<NavMap> navMaps) throws NavMapException {
                        LinkedList<NavMap> newMaps = new LinkedList<>();
                        for (NavMap backMap : navMaps) {
                            //Returned Data Is Just for Display, Converted To Actual Data
                            newMaps.add(NavMapConvert.convertToActual(backMap));
                        }

                        return newMaps;
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> getNavMap(String navMapId) {
        if (!mNeedCompat) {
            return this.mManager.getNavMap(navMapId);
        }

        Promise<NavMap, NavMapException> promise = this.mManager.getNavMap(navMapId);

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> addNavMap(NavMap navMap) {
        if (!mNeedCompat) {
            return this.mManager.addNavMap(navMap);
        }

        //The Incoming Is Actual Data, Converted To Display Data
        Promise<NavMap, NavMapException> promise = this.mManager.addNavMap(NavMapConvert.convertToDisplay(navMap));

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> modifyNavMap(NavMap navMap) {
        if (!mNeedCompat) {
            return this.mManager.modifyNavMap(navMap);
        }

        //The Incoming Is Actual Data, Converted To Display Data
        Promise<NavMap, NavMapException> promise = this.mManager.modifyNavMap(NavMapConvert.convertToDisplay(navMap));

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> removeNavMap(String navMapId) {
        if (!mNeedCompat) {
            return this.mManager.removeNavMap(navMapId);
        }

        Promise<NavMap, NavMapException> promise = this.mManager.removeNavMap(navMapId);

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> setCurrentNavMap(String navMapId) {
        if (!mNeedCompat) {
            return this.mManager.setCurrentNavMap(navMapId);
        }

        Promise<NavMap, NavMapException> promise = this.mManager.setCurrentNavMap(navMapId);

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> getCurrentNavMap() {
        if (!mNeedCompat) {
            return this.mManager.getCurrentNavMap();
        }

        Promise<NavMap, NavMapException> promise = this.mManager.getCurrentNavMap();

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Promise<NavMap, NavMapException> unsetCurrentNavMap() {
        if (!mNeedCompat) {
            return this.mManager.unsetCurrentNavMap();
        }

        Promise<NavMap, NavMapException> promise = this.mManager.unsetCurrentNavMap();

        return PromiseOperators.mapDone(promise,
                new Function<NavMap, NavMap, NavMapException>() {
                    @Override
                    public NavMap apply(NavMap backMap) throws NavMapException {
                        //Returned Data Is Just for Display, Converted To Actual Data
                        return NavMapConvert.convertToActual(backMap);
                    }
                }).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public Location getCurrentLocation() throws LocationException {
        Location location = this.mManager.getCurrentLocation();
        if (!mNeedCompat) {
            return location;
        }

        return new Location.Builder(new Point(
                //Returned Is Centimeters, Convert To Meters
                location.getPosition().getX() / NavMapConvert.DECIMAL,
                location.getPosition().getY() / NavMapConvert.DECIMAL))
                .setRotation(location.getRotation())
                .setZ(location.getZ())
                .build();
    }

    public ProgressivePromise<Location, LocatingException, LocatingProgress> locateSelf() {
        if (!mNeedCompat) {
            return this.mManager.locateSelf();
        }

        ProgressivePromise<Location, LocatingException, LocatingProgress> promise = this.mManager.locateSelf();

        return ProgressivePromiseOperators.mapDone(promise,
                new Function<Location, Location, LocatingException>() {
                    @Override
                    public Location apply(Location location) throws LocatingException {
                        return new Location.Builder(new Point(
                                //Returned Is Centimeters, Convert To Meters
                                location.getPosition().getX() / NavMapConvert.DECIMAL,
                                location.getPosition().getY() / NavMapConvert.DECIMAL))
                                .setRotation(location.getRotation())
                                .setZ(location.getZ())
                                .build();
                    }
                }
        ).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public ProgressivePromise<Location, LocatingException, LocatingProgress>
    locateSelf(LocatingOption option) {
        if (!mNeedCompat) {
            return this.mManager.locateSelf(option);
        }

        ProgressivePromise<Location, LocatingException, LocatingProgress> promise =
                this.mManager.locateSelf(new LocatingOption.Builder()
                        .setNearby(new Location.Builder(new Point(
                                //Incoming Is Meters, Converted To Centimeters
                                option.getNearby().getPosition().getX() * NavMapConvert.DECIMAL,
                                option.getNearby().getPosition().getY() * NavMapConvert.DECIMAL))
                                .setRotation(option.getNearby().getRotation())
                                .setZ(option.getNearby().getZ())
                                .build())
                        .setTimeout(option.getTimeout())
                        .build());

        return ProgressivePromiseOperators.mapDone(promise,
                new Function<Location, Location, LocatingException>() {
                    @Override
                    public Location apply(Location location) throws LocatingException {
                        return new Location.Builder(new Point(
                                //Returned Is Centimeters, Convert To Meters
                                location.getPosition().getX() / NavMapConvert.DECIMAL,
                                location.getPosition().getY() / NavMapConvert.DECIMAL))
                                .setRotation(location.getRotation())
                                .setZ(location.getZ())
                                .build();
                    }
                }
        ).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public boolean isLocatingSelf() {
        return this.mManager.isLocatingSelf();
    }

    @Override
    public boolean isSelfLocated() {
        return this.mManager.isSelfLocated();
    }

    public ProgressivePromise<Void, NavigationException, NavigationProgress>
    navigate(Location destination) {
        if (!mNeedCompat) {
            return this.mManager.navigate(destination);
        }

        ProgressivePromise<Void, NavigationException, NavigationProgress> promise = this.mManager.navigate(
                new NavigationOption.Builder(
                        new Location.Builder(new Point(
                                //Incoming Is Meters, Converted To Centimeters
                                destination.getPosition().getX() * NavMapConvert.DECIMAL,
                                destination.getPosition().getY() * NavMapConvert.DECIMAL))
                                .setRotation(destination.getRotation())
                                .setZ(destination.getZ())
                                .build()).build());

        return ProgressivePromiseOperators.mapProgress(promise,
                new Function<NavigationProgress, NavigationProgress, NavigationException>() {
                    @Override
                    public NavigationProgress apply(NavigationProgress progress) throws NavigationException {
                        return new NavigationProgress.Builder(progress.getProgress())
                                .setLocation(new Location.Builder(new Point(
                                        //Returned Is Centimeters, Convert To Meters
                                        progress.getLocation().getPosition().getX() / NavMapConvert.DECIMAL,
                                        progress.getLocation().getPosition().getY() / NavMapConvert.DECIMAL))
                                        .setRotation(progress.getLocation().getRotation())
                                        .setZ(progress.getLocation().getZ())
                                        .build())
                                .build();
                    }
                }
        ).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public ProgressivePromise<Void, NavigationException, NavigationProgress>
    navigate(NavigationOption option) {
        if (!mNeedCompat) {
            return this.mManager.navigate(option);
        }

        ProgressivePromise<Void, NavigationException, NavigationProgress> promise =
                this.mManager.navigate(new NavigationOption.Builder(
                        new Location.Builder(new Point(
                                //Incoming Is Meters, Converted To Centimeters
                                option.getDestination().getPosition().getX() * NavMapConvert.DECIMAL,
                                option.getDestination().getPosition().getY() * NavMapConvert.DECIMAL))
                                .setRotation(option.getDestination().getRotation())
                                .setZ(option.getDestination().getZ())
                                .build())
                        .setMaxSpeed(option.getMaxSpeed())
                        .setTrackMode(option.isTrackMode())
                        .setRetryCount(option.getRetryCount())
                        .setRetryInterval(option.getRetryInterval())
                        .build());

        return ProgressivePromiseOperators.mapProgress(promise,
                new Function<NavigationProgress, NavigationProgress, NavigationException>() {
                    @Override
                    public NavigationProgress apply(NavigationProgress progress) throws NavigationException {
                        return new NavigationProgress.Builder(progress.getProgress())
                                .setLocation(new Location.Builder(new Point(
                                        //Returned Is Centimeters, Convert To Meters
                                        progress.getLocation().getPosition().getX() / NavMapConvert.DECIMAL,
                                        progress.getLocation().getPosition().getY() / NavMapConvert.DECIMAL))
                                        .setRotation(progress.getLocation().getRotation())
                                        .setZ(progress.getLocation().getZ())
                                        .build())
                                .build();
                    }
                }
        ).cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                promise.cancel();
            }
        });
    }

    @Override
    public boolean isNavigating() {
        return this.mManager.isNavigating();
    }

    @Override
    public void registerListener(LocationListener listener) {
        synchronized (mListeners) {
            mListeners.register(listener);
        }
    }

    @Override
    public void unregisterListener(LocationListener listener) {
        synchronized (mListeners) {
            mListeners.unregister(listener);
        }
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            synchronized (mListeners) {
                mListeners.forEach(new Consumer<LocationListener>() {
                    @Override
                    public void accept(LocationListener listener) {
                        if (mNeedCompat) {
                            listener.onLocationChanged(new Location.Builder(new Point(
                                    //Returned Is Centimeters, Convert To Meters
                                    location.getPosition().getX() / NavMapConvert.DECIMAL,
                                    location.getPosition().getY() / NavMapConvert.DECIMAL))
                                    .setRotation(location.getRotation())
                                    .setZ(location.getZ())
                                    .build());
                        } else {
                            listener.onLocationChanged(location);
                        }
                    }
                });
            }
        }
    };
}
