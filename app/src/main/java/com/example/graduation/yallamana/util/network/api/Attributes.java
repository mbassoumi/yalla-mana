package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by m_7el on 12/29/2017.
 */

public class Attributes {
    @SerializedName("bank")
    @Expose
    private String bank;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("android_User")
    @Expose
    private String androidUser;

    public Attributes(String bank, String age, String androidUser) {
        this.bank = bank;
        this.age = age;
        this.androidUser = androidUser;
    }


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAndroidUser() {
        return androidUser;
    }

    public void setAndroidUser(String androidUser) {
        this.androidUser = androidUser;
    }
}
