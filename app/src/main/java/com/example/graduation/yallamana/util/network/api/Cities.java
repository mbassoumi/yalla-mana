package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by m_7el on 1/3/2018.
 */

public class Cities {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose

    private String lon;
    //public List<Cities> data = null;

    public Cities(){};
    public Cities(int id, Name name) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Cities(int id, Name name, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}

