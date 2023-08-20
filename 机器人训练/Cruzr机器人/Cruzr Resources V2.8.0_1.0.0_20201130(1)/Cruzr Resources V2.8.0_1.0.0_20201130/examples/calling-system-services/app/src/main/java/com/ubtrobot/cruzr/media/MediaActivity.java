package com.ubtrobot.cruzr.media;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ubtechinc.cruzr.media_sdk.manager.MediaPlayManager;
import com.ubtechinc.cruzr.media_sdk.manager.MusicStatusListener;
import com.ubtechinc.cruzr.media_sdk.manager.VideoStatusListener;
import com.ubtrobot.callingsystemservices.R;

public class MediaActivity extends Activity implements View.OnClickListener, MusicStatusListener, VideoStatusListener {
    private String TAG= MediaActivity.class.getSimpleName();
    private Button btn_register_music;
    private Button btn_unregister_music;
    private Button btn_register_video;
    private Button btn_unregister_video;
    private Button btn_play_music;
    private Button btn_play_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaPlayManager.getInstance().init(getApplicationContext());
        setContentView(R.layout.activity_media);
        initView();
        initListener();
    }



    private void initView() {
        btn_register_music=findViewById(R.id.btn_register_music);
        btn_unregister_music=findViewById(R.id.btn_unregister_music);
        btn_register_video= findViewById(R.id.btn_register_video);
        btn_unregister_video= findViewById(R.id.btn_unregister_video);
        btn_play_music=findViewById(R.id.btn_play_music);
        btn_play_video=findViewById(R.id.btn_play_video);
    }
    private void initListener() {
        btn_register_music.setOnClickListener(this);
        btn_unregister_music.setOnClickListener(this);
        btn_register_video.setOnClickListener(this);
        btn_unregister_video.setOnClickListener(this);
        btn_play_music.setOnClickListener(this);
        btn_play_video.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_music:
                try {
                    MediaPlayManager.getInstance().addMusicStatusListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_unregister_music:
                try {
                    MediaPlayManager.getInstance().removeMusicStatusListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_register_video:
                try {
                    MediaPlayManager.getInstance().addVideoStatusListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_unregister_video:
                try {
                    MediaPlayManager.getInstance().removeVideoStatusListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_play_music:
                try {
                    MediaPlayManager.getInstance().playMusic("/storage/emulated/0/Music/AnniesWonderland.mp3",false,"com.ubtrobot.callingsystemservices");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_play_video:
                try {
                    MediaPlayManager.getInstance().playVideo("/storage/emulated/0/Movies/OtherIntroduce.mp4",false,"com.ubtrobot.callingsystemservices");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    @Override
    public void onMusicStart() {
        Log.i(TAG,"onMusicStart callback") ;

    }

    @Override
    public void onMusicComplete() {
        Log.i(TAG,"onMusicComplete callback") ;
    }

    @Override
    public void onMusicStop() {
        Log.i(TAG,"onMusicStop callback") ;
    }

    @Override
    public void onVideoStart() {
        Log.i(TAG,"onVideoStart callback") ;
    }

    @Override
    public void onVideoComplete() {
        Log.i(TAG,"onVideoComplete callback") ;
    }

    @Override
    public void onVideoStop() {
        Log.i(TAG,"onVideoStop callback") ;
    }
}
