package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.GyroscopeSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class GyroscopeBroadcastReceiver extends BroadcastReceiver {
    public static final String Gyroscope_ACTION_RESP =
            "com.anil.compdemo.sensordemo.Gyroscope_MESSAGE_PROCESSED";

    public TextView GyroValX;
    public TextView GyroValY;
    public TextView GyroValZ;

    public GyroscopeBroadcastReceiver(TextView GyroscopeValX, TextView GyroscopeValY, TextView GyroscopeValZ) {
        this.GyroValX = GyroscopeValX;
        this.GyroValY = GyroscopeValY;
        this.GyroValZ = GyroscopeValZ;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String x = intent.getStringExtra(GyroscopeSensorService.Gyroscope_OUT_MSG_X);
        String y = intent.getStringExtra(GyroscopeSensorService.Gyroscope_OUT_MSG_Y);
        String z = intent.getStringExtra(GyroscopeSensorService.Gyroscope_OUT_MSG_Z);

        this.GyroValX.setText("X Axis: " + x);
        this.GyroValY.setText("Y Axis: " + y);
        this.GyroValZ.setText("Z Axis: " + z);

    }
}