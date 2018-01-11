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
    private List<Cities> cities=null ;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("trip")
    @Expose
    private Trip trip;
    @SerializedName("trips")
    @Expose
    private List<Trip> trips = null;
//    @SerializedName("unaccepted_trips")
//    @Expose
//    private List<UnacceptedTrip> unacceptedTrips = null;

    @SerializedName("past_trips")
    @Expose
    private List<Trip> pastTrips = null;
    @SerializedName("requested_trips")
    @Expose
    private List<Trip> requestTrips = null;
    @SerializedName("today_trips")
    @Expose
    private List<Trip> todayTrips = null;
    @SerializedName("future_trips")
    @Expose
    private List<Trip> futureTrips = null;
    @SerializedName("posts")
    @Expose
    private List<Post> posts = null;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Trip> getRequestTrips() {
        return requestTrips;
    }

    public void setRequestTrips(List<Trip> requestTrips) {
        this.requestTrips = requestTrips;
    }

    public List<Trip> getPastTrips() {
        return pastTrips;
    }

    public void setPastTrips(List<Trip> pastTrips) {
        this.pastTrips = pastTrips;
    }

    public List<Trip> getTodayTrips() {
        return todayTrips;
    }

    public void setTodayTrips(List<Trip> todayTrips) {
        this.todayTrips = todayTrips;
    }

    public List<Trip> getFutureTrips() {
        return futureTrips;
    }

    public void setFutureTrips(List<Trip> futureTrips) {
        this.futureTrips = futureTrips;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
//
//    public List<UnacceptedTrip> getUnacceptedTrips() {
//        return unacceptedTrips;
//    }
//
//    public void setUnacceptedTrips(List<UnacceptedTrip> unacceptedTrips) {
//        this.unacceptedTrips = unacceptedTrips;
//    }

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