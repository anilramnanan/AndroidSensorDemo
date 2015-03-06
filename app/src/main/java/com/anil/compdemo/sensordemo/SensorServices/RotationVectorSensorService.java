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
import com.anil.compdemo.sensordemo.SensorReceivers.RotationVectorBroadcastReceiver;

public class RotationVectorSensorService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelSensor;
    public static final String RotationVector_OUT_MSG_X = "R_X";
    public static final String RotationVector_OUT_MSG_Y = "R_Y";
    public static final String RotationVector_OUT_MSG_Z = "R_Z";
    public static final String RotationVector_OUT_MSG_S = "R_S";

    public static final String RotationVector_IN_MSG = "in";

    @Override
    public void onCreate() {

        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float s = event.values[3];

        String sensorResultX = String.valueOf(x);
        String sensorResultY = String.valueOf(y);
        String sensorResultZ = String.valueOf(z);
        String sensorResultS = String.valueOf(s);
        Intent broadcastIntent;
        broadcastIntent = new Intent();
        broadcastIntent.setAction(RotationVectorBroadcastReceiver.RotationVector_ACTION_RESP);
        broadcastIntent.putExtra(RotationVector_OUT_MSG_X, sensorResultX);
        broadcastIntent.putExtra(RotationVector_OUT_MSG_Y, sensorResultY);
        broadcastIntent.putExtra(RotationVector_OUT_MSG_Z, sensorResultZ);
        broadcastIntent.putExtra(RotationVector_OUT_MSG_S, sensorResultS);
        sendBroadcast(broadcastIntent);
        System.out.println("RotationVector sensor changing");

        DBHelper db = new DBHelper(this);
        db.addSensorRecord(new SensorModel("RotationVector Sensor",sensorResultX,sensorResultY,sensorResultZ));
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
