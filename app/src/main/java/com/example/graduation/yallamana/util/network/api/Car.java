package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.jar.Attributes;

/**
 * Created by m_7el on 12/29/2017.
 */

class Car {
    @SerializedName("car_licence")
    @Expose
    private Object carLicence;
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("model_year")
    @Expose
    private String modelYear;

    @SerializedName("model_car")
    @Expose
    private String modelCar;
    @SerializedName("seats_number")
    @Expose
    private String seatsNumber;

    @SerializedName("attributes")
    @Expose
    private Attributess attributes;


    public Object getCarLicence() {
        return carLicence;
    }

    public void setCarLicence(Object carLicence) {
        this.carLicence = carLicence;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(String seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Attributess getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributess attributes) {
        this.attributes = attributes;
    }


}

