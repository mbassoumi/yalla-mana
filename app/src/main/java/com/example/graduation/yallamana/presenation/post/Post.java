package com.example.graduation.yallamana.presenation.post;

/**
 * Created by m_7el on 12/10/2017.
 */

public class Post {
    private String name;
    private int image;
    private String post;
    private long timeCreation;
    private String date;

    public void setTimeCreation(long timeCreation) {
        this.timeCreation = timeCreation;
    }

    public int getImage() {
        return image;
    }


    public void setImage(int image) {
        this.image = image;

    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getpost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    public long getTimeCreation() {
        return timeCreation;
    }

    public String getDate() {
        return date;
    }
}
