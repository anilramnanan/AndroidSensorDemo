package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.LightSensorService;
import com.anil.compdemo.sensordemo.SensorServices.ProximitySensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class ProximityBroadcastReceiver extends BroadcastReceiver {
    public static final String PROXIMITY_ACTION_RESP =
            "com.anil.compdemo.sensordemo.PROXIMITY_MESSAGE_PROCESSED";

    public TextView proxVal;

    public ProximityBroadcastReceiver(TextView proximityVal) {
        this.proxVal = proximityVal;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(ProximitySensorService.PROXIMITY_OUT_MSG);
        this.proxVal.setText("Distance from object: " + text + " cm");

    }
}