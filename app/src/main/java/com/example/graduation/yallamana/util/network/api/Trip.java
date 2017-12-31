package com.example.graduation.yallamana.util.network.api;

public class Trip {
    private String from;

    private String To;
    private String date;
    private String price;
    private int numOfSongs;
    private int thumbnail;

    public Trip() {
    }

    public Trip(String from, String date, int thumbnail, String To,String price) {
        this.from = from;
        this.To = To;
        this.date = date;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
