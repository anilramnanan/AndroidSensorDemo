package com.anil.compdemo.sensordemo.SensorServices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.anil.compdemo.sensordemo.Database.DBHelper;
import com.anil.compdemo.sensordemo.Database.SensorModel;
import com.anil.compdemo.sensordemo.SensorReceivers.GPSBroadcastReceiver;

public class GPSSensorService extends Service  implements LocationListener {

    private final Context mContext;
    public static final String GPS_LAT_MSG = "LAT";
    public static final String GPS_LONG_MSG = "LONG";


    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;
    Location lastLocation;

    double currentLatitude;
    double currentLongitude;


    double lastLatitude;
    double lastLongitude;

    private static final long UPDATE_DIST = 10;
    private static final long UPDATE_TIME = 1000 * 60 * 1;
    protected LocationManager locationManager;

    public GPSSensorService(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, UPDATE_TIME,
                            UPDATE_DIST, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();
                        }
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, UPDATE_TIME,
                                UPDATE_DIST, this);
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                currentLatitude = location.getLatitude();
                                currentLongitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    public void stopGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSSensorService.this);
        }
    }

    public double getLatitude() {
        if (location != null) {
            currentLatitude = location.getLatitude();
        }
        return currentLatitude;
    }

    public double getLongitude() {
        if (location != null) {
            currentLongitude = location.getLongitude();
        }
        return currentLongitude;
    }

  public boolean isGPSEnabled() {
      return isGPSEnabled;

  }

  public boolean isNetworkEnabled() {
        return isNetworkEnabled;

  }

    @Override
    public void onLocationChanged(Location location) {
    }

    public void onDestroy() {
        stopGPS();
    }


    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}