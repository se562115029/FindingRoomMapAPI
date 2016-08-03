package com.example.oo.googlemapapidemo;

import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by oo on 28/7/2559.
 */
public class IRoom implements Serializable{
    private int _id;
    private String _roomcode;
    private String _buildingname;
    private String _note;
    private String _floor;
    private String _lat;
    private String _lng;

    public IRoom(){}

    public IRoom(String _note, String _buildingname, String _roomcode,String floor,String lat,String lng) {
        this._note = _note;
        this._buildingname = _buildingname;
        this._roomcode = _roomcode;
        this._floor = floor;
        this._lat = lat;
        this._lng = lng;
    }

    public void set_lng(String _lng) {
        this._lng = _lng;
    }

    public void set_lat(String _lat) {
        this._lat = _lat;
    }

    public String get_lng() {
        return _lng;
    }

    public String get_lat() {
        return _lat;
    }

    public String get_floor() {
        return _floor;
    }

    public void set_floor(String _floor) {
        this._floor = _floor;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_roomcode(String _roomcode) {
        this._roomcode = _roomcode;
    }

    public void set_buildingname(String _buildingname) {
        this._buildingname = _buildingname;
    }


    public int get_id() {
        return _id;
    }

    public String get_roomcode() {
        return _roomcode;
    }

    public String get_buildingname() {
        return _buildingname;
    }

}



