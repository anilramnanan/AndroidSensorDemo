package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.RotationVectorSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class RotationVectorBroadcastReceiver extends BroadcastReceiver {
    public static final String RotationVector_ACTION_RESP =
            "com.anil.compdemo.sensordemo.RotationVector_MESSAGE_PROCESSED";

    public TextView RotationVectorValX;
    public TextView RotationVectorValY;
    public TextView RotationVectorValZ;
    public TextView RotationVectorValS;

    public RotationVectorBroadcastReceiver(TextView RotationVectorValX,TextView RotationVectorValY,TextView RotationVectorValZ,TextView RotationVectorValS) {
        this.RotationVectorValX = RotationVectorValX;
        this.RotationVectorValY = RotationVectorValY;
        this.RotationVectorValZ = RotationVectorValZ;
        this.RotationVectorValS = RotationVectorValS;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String x = intent.getStringExtra(RotationVectorSensorService.RotationVector_OUT_MSG_X);
        String y = intent.getStringExtra(RotationVectorSensorService.RotationVector_OUT_MSG_Y);
        String z = intent.getStringExtra(RotationVectorSensorService.RotationVector_OUT_MSG_Z);
        String s = intent.getStringExtra(RotationVectorSensorService.RotationVector_OUT_MSG_S);
        this.RotationVectorValX.setText("X Axis: " + x);
        this.RotationVectorValY.setText("Y Axis: " + y);
        this.RotationVectorValZ.setText("Z Axis: " + z);
        this.RotationVectorValS.setText("Scalar: " + x);

    }
}