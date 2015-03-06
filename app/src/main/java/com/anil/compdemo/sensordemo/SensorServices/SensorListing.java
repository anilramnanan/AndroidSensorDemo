package com.anil.compdemo.sensordemo.SensorServices;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SensorListing implements SensorEventListener{

    /**
     * An array of sample (dummy) items.
     */
    public static List<SensorListItem> ITEMS = new ArrayList<SensorListItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, SensorListItem> ITEM_MAP = new HashMap<String, SensorListItem>();

    static {
        // Add 3 sample items.
        addItem(new SensorListItem("1", "Accelerometer"));
        addItem(new SensorListItem("2", "Magnetic Field"));
        addItem(new SensorListItem("3", "Gravity"));
        addItem(new SensorListItem("4", "Proximity"));
        addItem(new SensorListItem("5", "Pressure"));
        addItem(new SensorListItem("6", "Temperature"));
        addItem(new SensorListItem("7", "Linear Acceleration"));
        addItem(new SensorListItem("8", "Rotation Vector"));
        addItem(new SensorListItem("9", "Gyroscope"));
        addItem(new SensorListItem("10", "GPS"));




    }

    private static void addItem(SensorListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SensorListItem {
        public String id;
        public String content;

        public SensorListItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
