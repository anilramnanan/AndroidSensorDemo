package com.anil.compdemo.sensordemo.Database;

/**
 * Created by Anil on 3/1/15.
 */
public class SensorModel {


    int id;
    String sensortype;
    String valuex;
    String valuey;
    String valuez;
    String created_at;

    // constructors
    public SensorModel() {
    }

    public SensorModel(String sensortype, String valuex, String valuey, String valuez) {
        this.id = id;
        this.sensortype = sensortype;
        this.valuex = valuex;
        this.valuey = valuey;
        this.valuez = valuez;
    }



    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSensorType(String sensortype) {
        this.sensortype = sensortype;
    }

    public void setValueX(String valuex) {
        this.valuex = valuex;
    }

    public void setValueY(String valuey) {
        this.valuey = valuey;
    }

    public void setValueZ(String valuez) {
        this.valuez = valuez;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getSensorType() {
        return this.sensortype;
    }

    public String getValueX() {
        return this.valuex;
    }

    public String getValueY() {
        return this.valuey;
    }

    public String getValueZ() {
        return this.valuez;
    }


}