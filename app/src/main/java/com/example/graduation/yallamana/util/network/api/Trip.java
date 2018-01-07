package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by m_7el on 1/6/2018.
 */

public class Trip {

    @SerializedName("seats_number")
    @Expose
    private int seatsNumber;
    @SerializedName("from_city")
    @Expose
    private Cities startPoint;

    @SerializedName("to_city")
    @Expose
    private Cities endPoint;

    @SerializedName("driver")
    @Expose
    private User1 driver;
    @SerializedName("car")
    @Expose
    private Car car;
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
    @SerializedName("riders")
    @Expose
    private Riders riders;
    @SerializedName("can_cancel")
    @Expose
    private boolean cancle;
    @SerializedName("can_delete")
    @Expose
    private boolean delete;
    @SerializedName("can_reserve")
    @Expose
    private boolean reserve;

    public Trip(int seatsNumber, Cities startPoint, Cities endPoint,String date, double price, String stuats, TripAttr attributes) {
        this.seatsNumber = seatsNumber;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
        this.price = price;
        this.stuats = stuats;
        this.attributes = attributes;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Cities getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Cities startPoint) {
        this.startPoint = startPoint;
    }

    public Cities getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Cities endPoint) {
        this.endPoint = endPoint;
    }

    public User1 getDriver() {
        return driver;
    }

    public void setDriver(User1 driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public Riders getRiders() {
        return riders;
    }

    public void setRiders(Riders riders) {
        this.riders = riders;
    }

    public boolean isCancle() {
        return cancle;
    }

    public void setCancle(boolean cancle) {
        this.cancle = cancle;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
}