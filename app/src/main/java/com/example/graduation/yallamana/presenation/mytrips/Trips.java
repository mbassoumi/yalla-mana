package com.example.graduation.yallamana.presenation.mytrips;

/**
 * Created by m_7el on 12/6/2017.
 */

class Trips {
    private String from;
    private String to;


    private int image;
    private long timeCreation;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

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


    public long getTimeCreation() {
        return timeCreation;
    }

    public String getDate() {
        return date;
    }
}
