package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mais
 */

public class Comment1 {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("post_id")
    @Expose
    private int postID;
    @SerializedName("body")
    @Expose

    private String body;



    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



}
