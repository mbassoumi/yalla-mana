package com.example.graduation.yallamana.util.network.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by Mais
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    long TimeCreation;
    long postid;
    byte[] Image;
    String content;
    String location;
    String onlyme;
    long numComments;
    long numLikes;
    long numDisLikes;
    int myStatus;
    User user;

    public long getTimeCreation() {
        return TimeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        TimeCreation = timeCreation;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOnlyme() {
        return onlyme;
    }

    public void setOnlyme(String onlyme) {
        this.onlyme = onlyme;
    }

    public long getNumComments() {
        return numComments;
    }

    public void setNumComments(long numComments) {
        this.numComments = numComments;
    }

    public long getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(long numLikes) {
        this.numLikes = numLikes;
    }

    public long getNumDisLikes() {
        return numDisLikes;
    }

    public void setNumDisLikes(long numDisLikes) {
        this.numDisLikes = numDisLikes;
    }

    public int getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(int myStatus) {
        this.myStatus = myStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
