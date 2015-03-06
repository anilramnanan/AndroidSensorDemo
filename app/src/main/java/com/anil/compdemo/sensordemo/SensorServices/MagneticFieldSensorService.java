package com.anil.compdemo.sensordemo.SensorServices;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import com.anil.compdemo.sensordemo.Database.DBHelper;
import com.anil.compdemo.sensordemo.Database.SensorModel;
import com.anil.compdemo.sensordemo.SensorReceivers.MagneticFieldBroadcastReceiver;

public class MagneticFieldSensorService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelSensor;
    public static final String MagneticField_OUT_MSG_X = "MA_X";
    public static final String MagneticField_OUT_MSG_Y = "MA_Y";
    public static final String MagneticField_OUT_MSG_Z = "MA_Z";
    public static final String MagneticField_IN_MSG = "in";

    @Override
    public void onCreate() {

        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String sensorResultX = String.valueOf(x);
        String sensorResultY = String.valueOf(y);
        String sensorResultZ = String.valueOf(z);
        Intent broadcastIntent;
        broadcastIntent = new Intent();
        broadcastIntent.setAction(MagneticFieldBroadcastReceiver.MagneticField_ACTION_RESP);
        broadcastIntent.putExtra(MagneticField_OUT_MSG_X, sensorResultX);
        broadcastIntent.putExtra(MagneticField_OUT_MSG_Y, sensorResultY);
        broadcastIntent.putExtra(MagneticField_OUT_MSG_Z, sensorResultZ);
        sendBroadcast(broadcastIntent);
        System.out.println("Magnetic Field sensor changing");

        DBHelper db = new DBHelper(this);
        db.addSensorRecord(new SensorModel("Magnetic Field Sensor",sensorResultX,sensorResultY,sensorResultZ));
        System.out.println("writing to db " + sensorResultX);
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
