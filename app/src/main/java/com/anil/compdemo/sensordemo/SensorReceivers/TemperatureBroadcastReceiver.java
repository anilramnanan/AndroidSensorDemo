package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.TemperatureSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class TemperatureBroadcastReceiver extends BroadcastReceiver {
    public static final String Temperature_ACTION_RESP =
            "com.anil.compdemo.sensordemo.Temperature_MESSAGE_PROCESSED";

    public TextView TemperatureVal;

    public TemperatureBroadcastReceiver(TextView TemperatureVal) {
        this.TemperatureVal = TemperatureVal;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(TemperatureSensorService.Temperature_OUT_MSG);
        this.TemperatureVal.setText(text + "°C");

    }
}