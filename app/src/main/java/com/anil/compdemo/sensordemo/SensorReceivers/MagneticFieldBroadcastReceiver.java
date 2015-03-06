package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.MagneticFieldSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class MagneticFieldBroadcastReceiver extends BroadcastReceiver {
    public static final String MagneticField_ACTION_RESP =
            "com.anil.compdemo.sensordemo.MagneticField_MESSAGE_PROCESSED";

    public TextView MagneticFieldValX;
    public TextView MagneticFieldValY;
    public TextView MagneticFieldValZ;

    public MagneticFieldBroadcastReceiver(TextView MagneticFieldValX, TextView MagneticFieldValY, TextView MagneticFieldValZ) {
        this.MagneticFieldValX = MagneticFieldValX;
        this.MagneticFieldValY = MagneticFieldValY;
        this.MagneticFieldValZ = MagneticFieldValZ;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String x = intent.getStringExtra(MagneticFieldSensorService.MagneticField_OUT_MSG_X);
        String y = intent.getStringExtra(MagneticFieldSensorService.MagneticField_OUT_MSG_Y);
        String z = intent.getStringExtra(MagneticFieldSensorService.MagneticField_OUT_MSG_Z);
        this.MagneticFieldValX.setText("X Axis: " + x);
        this.MagneticFieldValY.setText("Y Axis: " + y);
        this.MagneticFieldValZ.setText("Z Axis: " + z);


    }
}