package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by m_7el on 1/6/2018.
 */

public class Riders {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("users")
    @Expose
    private List<User1> users;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User1> getUsers() {
        return users;
    }

    public void setUsers(List<User1> users) {
        this.users = users;
    }
}
