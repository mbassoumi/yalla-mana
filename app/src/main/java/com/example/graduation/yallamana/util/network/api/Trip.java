package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by m_7el on 1/6/2018.
 */

public class Trip  implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id ;
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
    private Date date;
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
    @SerializedName("has_user")
    @Expose
    private boolean hasUser;
    public Trip(int id, int seatsNumber, Cities startPoint, Cities endPoint,
                User1 driver, Car car, Date date, double price, String stuats,
                TripAttr attributes, Riders riders,boolean cancle,
                boolean delete, boolean reserve, boolean hasUser) {
        this.id = id;
        this.seatsNumber = seatsNumber;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.driver = driver;
        this.car = car;
        this.date = date;
        this.price = price;
        this.stuats = stuats;
        this.attributes = attributes;
        this.riders = riders;
        this.cancle = cancle;
        this.delete = delete;
        this.reserve = reserve;
        this.hasUser = hasUser;
    }

    protected Trip(Parcel in) {
        id = in.readInt();
        seatsNumber = in.readInt();
        price = in.readDouble();
        stuats = in.readString();
        cancle = in.readByte() != 0;
        delete = in.readByte() != 0;
        reserve = in.readByte() != 0;
        hasUser = in.readByte() != 0;
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(seatsNumber);
        dest.writeDouble(price);
        dest.writeString(stuats);
        dest.writeByte((byte) (cancle ? 1 : 0));
        dest.writeByte((byte) (delete ? 1 : 0));
        dest.writeByte((byte) (reserve ? 1 : 0));
        dest.writeByte((byte) (hasUser ? 1 : 0));
    }
}