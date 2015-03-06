package com.anil.compdemo.sensordemo.SensorReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.anil.compdemo.sensordemo.SensorServices.GPSSensorService;

/**
 * Created by Anil on 3/1/15.
 */

public class GPSBroadcastReceiver extends BroadcastReceiver {
    public static final String GPS_ACTION_RESP =
            "com.anil.compdemo.sensordemo.GPS_MESSAGE_PROCESSED";

    public TextView GPSLong;
    public TextView GPSLat;

    public GPSBroadcastReceiver(TextView GPSLat, TextView GPSLong) {
        this.GPSLong = GPSLong;
        this.GPSLat = GPSLat;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String glat = intent.getStringExtra(GPSSensorService.GPS_LAT_MSG);
        String glong = intent.getStringExtra(GPSSensorService.GPS_LONG_MSG);
        this.GPSLat.setText(glat);
        this.GPSLat.setText(glong);

    }
}