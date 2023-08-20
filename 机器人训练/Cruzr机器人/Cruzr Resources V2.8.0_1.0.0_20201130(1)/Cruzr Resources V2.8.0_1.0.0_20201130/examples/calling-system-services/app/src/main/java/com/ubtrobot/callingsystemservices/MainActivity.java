package com.ubtrobot.callingsystemservices;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ubtrobot.callingsystemservices.audio.AudioActivity;
import com.ubtrobot.callingsystemservices.diagnosis.DiagnosisActivity;
import com.ubtrobot.callingsystemservices.emotion.EmotionActivity;
import com.ubtrobot.callingsystemservices.light.LightActivity;
import com.ubtrobot.callingsystemservices.locomotion.LocomotionActivity;
import com.ubtrobot.callingsystemservices.motion.MotionActivity;
import com.ubtrobot.callingsystemservices.navigation.NavigationActivity;
import com.ubtrobot.callingsystemservices.orchestration.OrchestrationActivity;
import com.ubtrobot.callingsystemservices.part.PartActivity;
import com.ubtrobot.callingsystemservices.power.PowerActivity;
import com.ubtrobot.callingsystemservices.recharging.RechargingActivity;
import com.ubtrobot.callingsystemservices.resource.ResourceActivity;
import com.ubtrobot.callingsystemservices.sensor.SensorActivity;
import com.ubtrobot.callingsystemservices.servo.ServoActivity;
import com.ubtrobot.callingsystemservices.speech.SpeechActivity;
import com.ubtrobot.cruzr.OtherActivity;
import com.ubtrobot.rosa.Build;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Manager-app";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        showInfo();
    }

    private void showInfo() {
        ((TextView) findViewById(R.id.version)).setText(Build.VERSION);
    }

    public void speech(View view) {
        Log.i(TAG, "Click speech.");
        startActivity(new Intent(this, SpeechActivity.class));
    }

    public void navigation(View view) {
        Log.i(TAG, "Click navigation.");
        startActivity(new Intent(this, NavigationActivity.class));
    }

    public void motion(View view) {
        Log.i(TAG, "Click motion.");
        startActivity(new Intent(this, MotionActivity.class));
    }

    public void locomotion(View view) {
        Log.i(TAG, "Click locomotion.");
        startActivity(new Intent(this, LocomotionActivity.class));
    }

    public void light(View view) {
        Log.i(TAG, "Click light.");
        startActivity(new Intent(this, LightActivity.class));
    }

    public void emotion(View view) {
        Log.i(TAG, "Click emotion.");
        startActivity(new Intent(this, EmotionActivity.class));
    }

    public void resource(View view) {
        Log.i(TAG, "Click resource.");
        startActivity(new Intent(this, ResourceActivity.class));
    }

    public void orchestration(View view) {
        Log.i(TAG, "Click orchestration.");
        startActivity(new Intent(this, OrchestrationActivity.class));
    }

    public void power(View view) {
        Log.i(TAG, "Click power.");
        startActivity(new Intent(this, PowerActivity.class));
    }

    public void sensor(View view) {
        Log.i(TAG, "Click sensor.");
        startActivity(new Intent(this, SensorActivity.class));
    }

    public void servo(View view) {
        Log.i(TAG, "Click servo.");
        startActivity(new Intent(this, ServoActivity.class));
    }

    public void part(View view) {
        Log.i(TAG, "Click part.");
        startActivity(new Intent(this, PartActivity.class));
    }

    public void recharging(View view) {
        Log.i(TAG, "Click recharging.");
        startActivity(new Intent(this, RechargingActivity.class));
    }

    public void audio(View view) {
        Log.i(TAG, "Click audio.");
        startActivity(new Intent(this, AudioActivity.class));
    }

    public void diagnosis(View view) {
        Log.i(TAG, "Click diagnosis.");
        startActivity(new Intent(this, DiagnosisActivity.class));
    }
    public void others(View view){
        Log.i(TAG, "Click others.");
        startActivity(new Intent(this, OtherActivity.class));
    }

}
