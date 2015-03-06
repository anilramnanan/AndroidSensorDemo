package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.GravitySensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class GravityBroadcastReceiver extends BroadcastReceiver {
    public static final String Gravity_ACTION_RESP =
            "com.anil.compdemo.sensordemo.Gravity_MESSAGE_PROCESSED";

    public TextView GravityValX;
    public TextView GravityValY;
    public TextView GravityValZ;


    public GravityBroadcastReceiver(TextView GravityValX, TextView GravityValY, TextView GravityValZ) {
        this.GravityValX = GravityValX;
        this.GravityValY = GravityValY;
        this.GravityValZ = GravityValZ;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String x = intent.getStringExtra(GravitySensorService.Gravity_OUT_MSG_X);
        String y = intent.getStringExtra(GravitySensorService.Gravity_OUT_MSG_Y);
        String z = intent.getStringExtra(GravitySensorService.Gravity_OUT_MSG_Z);
        this.GravityValX.setText("X Axis: " + x);
        this.GravityValY.setText("Y Axis: " + y);
        this.GravityValZ.setText("Z Axis: " + z);

    }
}