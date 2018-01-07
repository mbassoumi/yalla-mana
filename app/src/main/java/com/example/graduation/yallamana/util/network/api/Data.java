package com.example.graduation.yallamana.util.network.api;

/**
 * Created by Mais
 */import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("user")
    @Expose
    private User1 user;
    @SerializedName("new_user")
    @Expose
    private Boolean newUser;
    @SerializedName("cities")
    @Expose
    private List<Cities> cities ;
    @SerializedName("token")
    @Expose
    private String token;

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}