package com.example.graduation.yallamana.util.network.api;

import java.sql.Date;

/**
 * Created by Mais
 */

public class User {
    UserID id;
    String Password;
    long TimeCreation;
    String FirstName;
    String LastName;
    String Gender;
    Date Birthday;
    byte[] Picture;

    public UserID getId() {
        return id;
    }

    public void setId(UserID id) {
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getTimeCreation() {
        return TimeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        TimeCreation = timeCreation;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public byte[] getPicture() {
        return Picture;
    }

    public void setPicture(byte[] picture) {
        Picture = picture;
    }
}
