package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by m_7el on 12/29/2017.
 */

public class Car {

    @SerializedName("car_licence")
    @Expose
    private String carLicence;
    @SerializedName("model_year")
    @Expose
    private String modelYear;
    @SerializedName("model_type")
    @Expose
    private String modelType;
    @SerializedName("seats_number")
    @Expose
    private int seatsNumber;
    @SerializedName("attributes")
    @Expose

    private Attributes attributes;
    @SerializedName("photo")
    @Expose
    private File photo;

    protected Car(Parcel in) {
        carLicence = in.readString();
        modelYear = in.readString();
        modelType = in.readString();
        seatsNumber = in.readInt();
    }


    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getCarLicence() {
        return carLicence;
    }

    public void setCarLicence(String carLicence) {
        this.carLicence = carLicence;
    }


    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }


    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }


    public Car(String carLicence, String modelYear, String modelType, int seatsNumber, Attributes attributes) {
        this.carLicence = carLicence;
        this.modelYear = modelYear;
        this.modelType = modelType;
        this.seatsNumber = seatsNumber;
        this.attributes = attributes;

    }
}