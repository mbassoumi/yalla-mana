package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by m_7el on 12/29/2017.
 */

public class NewUser implements Serializable {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("driving_licence")
    @Expose
    private String driverLicence;
    @SerializedName("type")

    @Expose
    private String type;

    @SerializedName("car")
    @Expose
    private Car car;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewUser(String name, String phone, String email, String gender, String photo, String driverLicence, String type, Car car) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.driverLicence = driverLicence;
        this.type = type;
        this.car = car;
        this.gender = gender;
        this.email = email;
    }

    public NewUser(String name, String phone, String email, String type, String gender, String photo) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.gender = gender;
        this.photo = photo;
    }

}
