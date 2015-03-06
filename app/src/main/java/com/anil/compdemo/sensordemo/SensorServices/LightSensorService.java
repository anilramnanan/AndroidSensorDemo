package com.anil.compdemo.sensordemo.SensorServices;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;

import com.anil.compdemo.sensordemo.Database.DBHelper;
import com.anil.compdemo.sensordemo.Database.SensorModel;
import com.anil.compdemo.sensordemo.SensorReceivers.LightBroadcastReceiver;

public class LightSensorService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelSensor;
    public static final String LIGHT_OUT_MSG = "out";
    public static final String LIGHT_IN_MSG = "in";

    @Override
    public void onCreate() {

        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];

        String sensorResult = String.valueOf(x);
        Intent broadcastIntent;
        broadcastIntent = new Intent();
        broadcastIntent.setAction(LightBroadcastReceiver.LIGHT_ACTION_RESP);
        broadcastIntent.putExtra(LIGHT_OUT_MSG, sensorResult);
        sendBroadcast(broadcastIntent);
        System.out.println("light sensor changing");

        DBHelper db = new DBHelper(this);
        db.addSensorRecord(new SensorModel("Light Sensor",sensorResult,"0","0"));
        System.out.println("writing to db " + sensorResult);
        db.close();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("sensor changing onAccuracyChanged");
    }
    public void onDestroy() {
        sensorManager.unregisterListener(this,accelSensor);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
