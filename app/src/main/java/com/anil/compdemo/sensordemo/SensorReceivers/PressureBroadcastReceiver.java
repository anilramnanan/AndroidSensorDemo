package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.PressureSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class PressureBroadcastReceiver extends BroadcastReceiver {
    public static final String Pressure_ACTION_RESP =
            "com.anil.compdemo.sensordemo.Pressure_MESSAGE_PROCESSED";

    public TextView PressureVal;

    public PressureBroadcastReceiver(TextView PressureVal) {
        this.PressureVal = PressureVal;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(PressureSensorService.Pressure_OUT_MSG);
        this.PressureVal.setText(text + "hPa");

    }
}