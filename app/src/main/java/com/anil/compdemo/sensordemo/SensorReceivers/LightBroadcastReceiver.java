package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.LightSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class LightBroadcastReceiver extends BroadcastReceiver {
    public static final String LIGHT_ACTION_RESP =
            "com.anil.compdemo.sensordemo.LIGHT_MESSAGE_PROCESSED";

    public TextView lightVal;

    public LightBroadcastReceiver(TextView lightVal) {
        this.lightVal = lightVal;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(LightSensorService.LIGHT_OUT_MSG);
        this.lightVal.setText(text + "lx");

    }
}