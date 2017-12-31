package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.jar.Attributes;

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

        private Attributess attributes;
        @SerializedName("photo")
        @Expose
        private File photo;

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


        public Attributess getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributess attributes) {
            this.attributes = attributes;
        }


        public Car(String carLicence, String modelYear, String modelType,int seatsNumber, Attributess attributes) {
            this.carLicence = carLicence;
            this.modelYear = modelYear;
            this.modelType = modelType;
            this.seatsNumber = seatsNumber;
            this.attributes = attributes;

        }

}