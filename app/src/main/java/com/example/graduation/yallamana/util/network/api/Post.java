package com.example.graduation.yallamana.util.network.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Mais
 */
public class Post implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("created_by")
    @Expose
    private User1 user;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_at")
    @Expose
    private Date time;


    @SerializedName("comments")
    @Expose
    private List<Comment> comments;


    protected Post(Parcel in) {
        id = in.readInt();
        body = in.readString();
        comments = in.createTypedArrayList(Comment.CREATOR);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(body);
        dest.writeTypedList(comments);
    }
}
