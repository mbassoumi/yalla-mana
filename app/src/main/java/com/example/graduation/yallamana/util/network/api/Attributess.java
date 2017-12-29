package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by m_7el on 12/29/2017.
 */

class Attributess {
    @SerializedName("color")
    @Expose
    private String color ;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("wheels")

    @Expose
    private String wheels;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }


}
