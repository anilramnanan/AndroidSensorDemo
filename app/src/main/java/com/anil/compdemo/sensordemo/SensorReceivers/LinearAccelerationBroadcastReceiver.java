package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.LinearAccelerationSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class LinearAccelerationBroadcastReceiver extends BroadcastReceiver {
    public static final String LinearAcceleration_ACTION_RESP =
            "com.anil.compdemo.sensordemo.LinearAcceleration_MESSAGE_PROCESSED";

    public TextView LinearAccelerationValX;
    public TextView LinearAccelerationValY;
    public TextView LinearAccelerationValZ;

    public LinearAccelerationBroadcastReceiver(TextView LinearAccelerationValX, TextView LinearAccelerationValY, TextView LinearAccelerationValZ) {
        this.LinearAccelerationValX = LinearAccelerationValX;
        this.LinearAccelerationValY = LinearAccelerationValY;
        this.LinearAccelerationValZ = LinearAccelerationValZ;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String x = intent.getStringExtra(LinearAccelerationSensorService.LinearAcceleration_OUT_MSG_X);
        String y = intent.getStringExtra(LinearAccelerationSensorService.LinearAcceleration_OUT_MSG_Y);
        String z = intent.getStringExtra(LinearAccelerationSensorService.LinearAcceleration_OUT_MSG_Z);
        this.LinearAccelerationValX.setText("X Axis: " + x);
        this.LinearAccelerationValY.setText("Y Axis: " + y);
        this.LinearAccelerationValZ.setText("Z Axis: " + z);

    }
}