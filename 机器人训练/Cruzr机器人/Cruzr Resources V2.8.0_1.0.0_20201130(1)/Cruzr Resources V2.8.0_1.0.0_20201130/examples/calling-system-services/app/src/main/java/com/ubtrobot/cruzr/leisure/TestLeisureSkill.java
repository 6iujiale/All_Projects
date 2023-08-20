package com.ubtrobot.cruzr.leisure;

import android.content.Intent;
import android.util.Log;

import com.ubtrobot.annotation.OnDirective;
import com.ubtrobot.skill.Directive;
import com.ubtrobot.skill.RobotBackgroundSkill;
import com.ubtrobot.speech.UnderstandingResult;

public class TestLeisureSkill extends RobotBackgroundSkill {
    @OnDirective(action = "/test/leisure/start/haha")
    public void leisureStart(Directive skillIntent, UnderstandingResult result) {
        Log.i("rqh_leisure","The leisure start success");
        stopSelf();
    }
}
