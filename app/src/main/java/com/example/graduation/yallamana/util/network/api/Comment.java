package com.example.graduation.yallamana.util.network.api;

/**
 * Created by Mais
 */

public class Comment {
    long commentid;
    String content;
    long TimeCreation;
    long numLikes;
    long numDisLikes;
    int myStatus;

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimeCreation() {
        return TimeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        TimeCreation = timeCreation;
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
}
