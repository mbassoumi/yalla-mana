package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by m_7el on 1/6/2018.
 */

public class NewTrip {


    @SerializedName("seats_number")
    @Expose
    private int seatsNumber;
    @SerializedName("from_city_id")
    @Expose
    private int idFrom;

    @SerializedName("to_city_id")
    @Expose
    private int idTo;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("status")
    @Expose
    private String stuats;
    @SerializedName("attributes")
    @Expose
    private TripAttr attributes;
    @SerializedName("token")
    @Expose
    private String token;

    public NewTrip(int seatsNumber, int idFrom, int idTo, String date, double price, String stuats, TripAttr attributes) {
        this.seatsNumber = seatsNumber;
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.date = date;
        this.price = price;
        this.stuats = stuats;
        this.attributes = attributes;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStuats() {
        return stuats;
    }

    public void setStuats(String stuats) {
        this.stuats = stuats;
    }

    public TripAttr getAttributes() {
        return attributes;
    }

    public void setAttributes(TripAttr attributes) {
        this.attributes = attributes;
    }
}

