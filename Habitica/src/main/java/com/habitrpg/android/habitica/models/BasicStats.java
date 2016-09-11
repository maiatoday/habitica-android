package com.habitrpg.android.habitica.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class BasicStats implements RealmModel {

    @PrimaryKey
    String id;
    public Float con, str, per;
    @SerializedName("int")
    public Float _int;

    private Boolean snowball;
    private Boolean streaks;

    public Float getCon() {
        return con != null ? con : Float.valueOf(0);
    }

    public void setCon(Float con) {
        this.con = con;
    }

    public Float getStr() {
        return str != null ? str : Float.valueOf(0);
    }

    public void setStr(Float str) {
        this.str = str;
    }

    public Float getPer() {
        return per != null ? per : Float.valueOf(0);
    }

    public void setPer(Float per) {
        this.per = per;
    }

    public Float get_int() {
        return _int != null ? _int : Float.valueOf(0);
    }

    public void set_int(Float _int) {
        this._int = _int;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSnowball() {
        return snowball;
    }

    public void setSnowball(Boolean snowball) {
        this.snowball = snowball;
    }

    public Boolean getStreaks() {
        return streaks;
    }

    public void setStreaks(Boolean streaks) {
        this.streaks = streaks;
    }

    public void merge(BasicStats stats) {
        if (stats == null) {
            return;
        }
        this.con = stats.con != null ? stats.con : this.con;
        this.str = stats.str != null ? stats.str : this.str;
        this.per = stats.per != null ? stats.per : this.per;
        this._int = stats._int != null ? stats._int : this._int;
    }
}

