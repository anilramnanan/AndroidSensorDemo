package com.anil.compdemo.sensordemo.Database;

/**
 * Created by Anil on 3/1/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_SENSORLOG = "sensorlog";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SENSORTYPE = "sensortype";
    public static final String COLUMN_VALUEX = "valuex";
    public static final String COLUMN_VALUEY = "valuey";
    public static final String COLUMN_VALUEZ = "valuez";

    private static final String DATABASE_NAME = "sensordemo.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SENSORLOG + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_SENSORTYPE + " text not null, "
            + COLUMN_VALUEX + " text not null, "
            + COLUMN_VALUEY + " text not null, "
            + COLUMN_VALUEZ + " text not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSORLOG);
        onCreate(db);
    }
    public long addSensorRecord(SensorModel sensor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID, sensor.getId());
        values.put(COLUMN_SENSORTYPE, sensor.getSensorType());
        values.put(COLUMN_VALUEX, sensor.getValueX());
        values.put(COLUMN_VALUEY, sensor.getValueY());
        values.put(COLUMN_VALUEZ, sensor.getValueZ());

        // insert row
        long sensor_id = db.insert(TABLE_SENSORLOG, null, values);
        return sensor_id;
    }

    public List<SensorModel> getAllSensorRecords() {
        List<SensorModel> sensorlog = new ArrayList<SensorModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_SENSORLOG ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                SensorModel sensorItem = new SensorModel();
                sensorItem.setId(c.getInt((c.getColumnIndex(COLUMN_ID))));
                sensorItem.setSensorType((c.getString(c.getColumnIndex(COLUMN_SENSORTYPE))));
                sensorItem.setValueX((c.getString(c.getColumnIndex(COLUMN_VALUEX))));
                sensorItem.setValueY((c.getString(c.getColumnIndex(COLUMN_VALUEY))));
                sensorItem.setValueZ((c.getString(c.getColumnIndex(COLUMN_VALUEZ))));
                //sensorItem.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                sensorlog.add(sensorItem);
            } while (c.moveToNext());
        }

        return sensorlog;
    }


}