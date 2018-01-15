package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mais
 */

public class Comment implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("post_id")
    @Expose
    private int postID;
    @SerializedName("body")
    @Expose

    private String body;
    @SerializedName("created_at")
    @Expose
    private Date time;

    @SerializedName("created_by")
    @Expose
    private User1 userID;


    protected Comment(Parcel in) {
        id = in.readInt();
        postID = in.readInt();
        body = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }


    public User1 getUserID() {
        return userID;
    }

    public void setUserID(User1 userID) {
        this.userID = userID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(postID);
        dest.writeString(body);
    }
}
