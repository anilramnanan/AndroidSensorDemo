package com.anil.compdemo.sensordemo;


// Student ID: 04742482

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.anil.compdemo.sensordemo.Database.DBHelper;
import com.anil.compdemo.sensordemo.Database.SensorModel;
import com.anil.compdemo.sensordemo.SensorReceivers.AccelerometerBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.GPSBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.GravityBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.GyroscopeBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.LightBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.LinearAccelerationBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.MagneticFieldBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.PressureBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.ProximityBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.RotationVectorBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorReceivers.TemperatureBroadcastReceiver;
import com.anil.compdemo.sensordemo.SensorServices.AccelerometerSensorService;
import com.anil.compdemo.sensordemo.SensorServices.GPSSensorService;
import com.anil.compdemo.sensordemo.SensorServices.GravitySensorService;
import com.anil.compdemo.sensordemo.SensorServices.GyroscopeSensorService;
import com.anil.compdemo.sensordemo.SensorServices.LightSensorService;
import com.anil.compdemo.sensordemo.SensorServices.LinearAccelerationSensorService;
import com.anil.compdemo.sensordemo.SensorServices.MagneticFieldSensorService;
import com.anil.compdemo.sensordemo.SensorServices.PressureSensorService;
import com.anil.compdemo.sensordemo.SensorServices.ProximitySensorService;
import com.anil.compdemo.sensordemo.SensorServices.RotationVectorSensorService;
import com.anil.compdemo.sensordemo.SensorServices.TemperatureSensorService;

import java.util.List;

public class MainActivity extends Activity {

    public static TextView lightValue;
    public static TextView proximityValue;
    private LightBroadcastReceiver lightReceiver;
    private ProximityBroadcastReceiver proximityReceiver;
    private AccelerometerBroadcastReceiver accelReceiver;
    private GPSBroadcastReceiver gpsReceiver;
    private GyroscopeBroadcastReceiver gyroReceiver;
    private GravityBroadcastReceiver gravReceiver;
    private LinearAccelerationBroadcastReceiver laReceiver;
    private MagneticFieldBroadcastReceiver magReceiver;
    private PressureBroadcastReceiver pressureReceiver;
    private RotationVectorBroadcastReceiver rvReceiver;
    private TemperatureBroadcastReceiver tempReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startLightService (View v) {

        ToggleButton toggleLight = (ToggleButton) findViewById(R.id.toggleLight);
        TextView lightInput = (TextView) findViewById(R.id.light_value);
        String strInputMsg = lightInput.getText().toString();
        lightInput.setText("Starting Light Service");
        Intent msgIntent = new Intent(this, LightSensorService.class);
        msgIntent.putExtra(LightSensorService.LIGHT_IN_MSG, strInputMsg);

        if (toggleLight.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(LightBroadcastReceiver.LIGHT_ACTION_RESP);
            lightReceiver = new LightBroadcastReceiver(lightInput);
            registerReceiver(lightReceiver, filter);

        } else {
            lightInput.setText("Light Service Stopped");
            unregisterReceiver(lightReceiver);
            stopService(new Intent(this, LightSensorService.class));
        }


    }

    public void startAccelerometerService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleAccel);
        TextView xInput = (TextView) findViewById(R.id.accel_valuex);
        TextView yInput = (TextView) findViewById(R.id.accel_valuey);
        TextView zInput = (TextView) findViewById(R.id.accel_valuez);

        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Accelerometer Service");
        Intent msgIntent = new Intent(this, AccelerometerSensorService.class);
        msgIntent.putExtra(AccelerometerSensorService.Accelerometer_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(AccelerometerBroadcastReceiver.ACCELEROMETER_ACTION_RESP);
            accelReceiver = new AccelerometerBroadcastReceiver(xInput, yInput, zInput);
            registerReceiver(accelReceiver, filter);

        } else {
            xInput.setText("Accelerometer Service Stopped");
            yInput.setText("");
            zInput.setText("");
            unregisterReceiver(accelReceiver);
            stopService(new Intent(this, AccelerometerSensorService.class));
        }


    }



    public void startGravityService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleGrav);
        TextView xInput = (TextView) findViewById(R.id.grav_valuex);
        TextView yInput = (TextView) findViewById(R.id.grav_valuey);
        TextView zInput = (TextView) findViewById(R.id.grav_valuez);
        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Gravity Service");
        Intent msgIntent = new Intent(this, GravitySensorService.class);
        msgIntent.putExtra(GravitySensorService.Gravity_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(GravityBroadcastReceiver.Gravity_ACTION_RESP);
            gravReceiver = new GravityBroadcastReceiver(xInput,yInput,zInput);
            registerReceiver(gravReceiver, filter);

        } else {
            xInput.setText("Gravity Service Stopped");
            yInput.setText("");
            zInput.setText("");
            unregisterReceiver(gravReceiver);
            stopService(msgIntent);
        }


    }


    public void startGyroscopeService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglegyro);
        TextView xInput = (TextView) findViewById(R.id.gyro_valuex);
        TextView yInput = (TextView) findViewById(R.id.gyro_valuey);
        TextView zInput = (TextView) findViewById(R.id.gyro_valuez);
        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Gyroscope Service");
        Intent msgIntent = new Intent(this, GyroscopeSensorService.class);
        msgIntent.putExtra(GyroscopeSensorService.Gyroscope_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(GyroscopeBroadcastReceiver.Gyroscope_ACTION_RESP);
            gyroReceiver = new GyroscopeBroadcastReceiver(xInput,yInput,zInput);
            registerReceiver(gyroReceiver, filter);

        } else {
            xInput.setText("Gyroscope Service Stopped");
            unregisterReceiver(gyroReceiver);
            stopService(msgIntent);
        }


    }

    public void startLinearAccelerationService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglela);
        TextView xInput = (TextView) findViewById(R.id.la_valuex);
        TextView yInput = (TextView) findViewById(R.id.la_valuey);
        TextView zInput = (TextView) findViewById(R.id.la_valuez);
        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Linear Acceleration Service Service");
        Intent msgIntent = new Intent(this, LinearAccelerationSensorService.class);
        msgIntent.putExtra(LinearAccelerationSensorService.LinearAcceleration_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(LinearAccelerationBroadcastReceiver.LinearAcceleration_ACTION_RESP);
            laReceiver = new LinearAccelerationBroadcastReceiver(xInput,yInput,zInput);
            registerReceiver(laReceiver, filter);

        } else {
            xInput.setText("Linear Acceleration Service Stopped");
            unregisterReceiver(laReceiver);
            stopService(msgIntent);


        }


    }

    public void startPressureService (View v) {

        ToggleButton toggleLight = (ToggleButton) findViewById(R.id.togglepressure);
        TextView lightInput = (TextView) findViewById(R.id.pressure_valuex);
        String strInputMsg = lightInput.getText().toString();
        lightInput.setText("Starting Pressure Service");
        Intent msgIntent = new Intent(this,PressureSensorService.class);
        msgIntent.putExtra(PressureSensorService.Pressure_IN_MSG, strInputMsg);

        if (toggleLight.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(PressureBroadcastReceiver.Pressure_ACTION_RESP);
            pressureReceiver = new PressureBroadcastReceiver(lightInput);
            registerReceiver(pressureReceiver, filter);

        } else {
            lightInput.setText("Pressure Service Stopped");
            unregisterReceiver(pressureReceiver);
            stopService(msgIntent);
        }


    }

    public void startTempService (View v) {

        ToggleButton toggleLight = (ToggleButton) findViewById(R.id.toggletemp);
        TextView lightInput = (TextView) findViewById(R.id.temp_valuex);
        String strInputMsg = lightInput.getText().toString();
        lightInput.setText("Starting Temperature Service");
        Intent msgIntent = new Intent(this, TemperatureSensorService.class);
        msgIntent.putExtra(TemperatureSensorService.Temperature_IN_MSG, strInputMsg);

        if (toggleLight.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(TemperatureBroadcastReceiver.Temperature_ACTION_RESP);
            tempReceiver = new TemperatureBroadcastReceiver(lightInput);
            registerReceiver(tempReceiver, filter);

        } else {
            lightInput.setText("Stopping Temperature");
            unregisterReceiver(tempReceiver);
            stopService(msgIntent);
        }


    }


    public void startRotationService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglerv);
        TextView xInput = (TextView) findViewById(R.id.rv_valuex);
        TextView yInput = (TextView) findViewById(R.id.rv_valuey);
        TextView zInput = (TextView) findViewById(R.id.rv_valuez);
        TextView sInput = (TextView) findViewById(R.id.rv_values);
        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Rotation Vector Service Service");
        Intent msgIntent = new Intent(this, RotationVectorSensorService.class);
        msgIntent.putExtra(RotationVectorSensorService.RotationVector_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(RotationVectorBroadcastReceiver.RotationVector_ACTION_RESP);
            rvReceiver = new RotationVectorBroadcastReceiver(xInput,yInput,zInput, sInput);
            registerReceiver(rvReceiver, filter);

        } else {
            xInput.setText("Rotation Vector Service Stopped");
            unregisterReceiver(rvReceiver);
            stopService(msgIntent);


        }


    }


    public void startMagneticFieldService (View v) {

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglemf);
        TextView xInput = (TextView) findViewById(R.id.mf_valuex);
        TextView yInput = (TextView) findViewById(R.id.mf_valuey);
        TextView zInput = (TextView) findViewById(R.id.mf_valuez);
        String strInputMsg = xInput.getText().toString();
        xInput.setText("Starting Magnetic Field Service Service");
        Intent msgIntent = new Intent(this, MagneticFieldSensorService.class);
        msgIntent.putExtra(MagneticFieldSensorService.MagneticField_IN_MSG, strInputMsg);

        if (toggleButton.isChecked()) {

            startService(msgIntent);

            IntentFilter filter = new IntentFilter(MagneticFieldBroadcastReceiver.MagneticField_ACTION_RESP);
            magReceiver = new MagneticFieldBroadcastReceiver(xInput,yInput,zInput);
            registerReceiver(magReceiver, filter);

        } else {
            xInput.setText("Magnetic Field Service Stopped");
            unregisterReceiver(magReceiver);
            stopService(msgIntent);


        }


    }



    public void startProximityService (View v) {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleProximity);
        TextView proximityInput = (TextView) findViewById(R.id.proximity_value);
        String strInputMsg = proximityInput.getText().toString();
        proximityInput.setText("Starting Proximity Service");
        Intent msgIntent = new Intent(this, ProximitySensorService.class);
        msgIntent.putExtra(ProximitySensorService.PROXIMITY_IN_MSG, strInputMsg);
        startService(msgIntent);

        if (toggleButton.isChecked()) {
            IntentFilter filter = new IntentFilter(ProximityBroadcastReceiver.PROXIMITY_ACTION_RESP);
            proximityReceiver = new ProximityBroadcastReceiver(proximityInput);
            registerReceiver(proximityReceiver, filter);

        } else {
            proximityInput.setText("Proximity Sensor Service Stopped");
            unregisterReceiver(proximityReceiver);
            stopService(msgIntent);
        }
    }

    public void startGPSService (View v) {


        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleGPS);
        GPSSensorService gps = new GPSSensorService(this);
        TextView longInput = (TextView) findViewById(R.id.gps_long);
        TextView latInput = (TextView) findViewById(R.id.gps_lat);
        TextView gpsStatus = (TextView) findViewById(R.id.gps_status);
        TextView netStatus = (TextView) findViewById(R.id.net_status);
        if (toggleButton.isChecked()) {



            double longValue = gps.getLatitude();
            double latValue = gps.getLongitude();
            if (gps.isGPSEnabled()) {
                gpsStatus.setText("GPS is ON");

            } else {
                gpsStatus.setText("GPS is OF: Getting last know location");

            }

            if (gps.isNetworkEnabled()) {
                netStatus.setText("Network is ON");

            } else {
                netStatus.setText("Network is OF: Getting last know location");

            }

            longInput.setText("Longitude: " + String.valueOf(longValue));
            latInput.setText("Latitude: " + String.valueOf(latValue));

        } else {

            gps.stopGPS();
            longInput.setText("GPS Service Stopped");
        }



    }


    public void saveData(View v) {

        DBHelper db = new DBHelper(this);
        List<SensorModel> contacts = db.getAllSensorRecords();
        //Log.d("records", contacts.toString());

        for (SensorModel cn : contacts) {
            String log = "Id: " + cn.getId() + " ,SensorType: " + cn.getSensorType() + " ,ValueX " + cn.getValueX() + " ,ValueY: " + cn.getValueY();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

    }

    public void showLog(View v) {

        Intent intent = new Intent(this, SensorLog.class);
        startActivity(intent);

    }





    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
