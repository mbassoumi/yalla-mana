package com.example.graduation.yallamana.util.network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by m_7el on 1/6/2018.
 */

public class TripAttr {
    @SerializedName("boys")
    @Expose
private boolean boys;
    @SerializedName("smoking")
    @Expose
    private boolean smoke;
    @SerializedName("wifi")
    @Expose
    private  boolean wifi;
    @SerializedName("girls")
    @Expose
    private boolean girls;
    @SerializedName("music")
    @Expose
    private boolean music;

    public TripAttr(boolean boys, boolean smoke, boolean wifi, boolean girls, boolean music) {
        this.boys = boys;
        this.smoke = smoke;
        this.wifi = wifi;
        this.girls = girls;
        this.music = music;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isBoys() {
        return boys;
    }

    public void setBoys(boolean boys) {
        this.boys = boys;
    }

    public boolean isSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isGirls() {
        return girls;
    }

    public void setGirls(boolean girls) {
        this.girls = girls;
    }
}
