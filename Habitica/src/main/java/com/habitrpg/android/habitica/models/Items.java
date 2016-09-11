package com.habitrpg.android.habitica.models;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.HashMap;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class Items implements RealmModel {

    @PrimaryKey
    String user_id;
    private String currentMount, currentPet;
    private int lastDrop_count;
    private Date lastDrop_date;

    private Gear gear;

    public Items(String currentMount, String currentPet, int lastDrop_count, Date lastDrop_date) {
        this.currentMount = currentMount;
        this.currentPet = currentPet;
        this.lastDrop_count = lastDrop_count;
        this.lastDrop_date = lastDrop_date;
    }

    public Items() {
    }

    public String getCurrentMount() {
        return currentMount;
    }

    public void setCurrentMount(String currentMount) {
        this.currentMount = currentMount;
    }

    public String getCurrentPet() {
        return currentPet;
    }

    public void setCurrentPet(String currentPet) {
        this.currentPet = currentPet;
    }

    public int getLastDrop_count() {
        return lastDrop_count;
    }

    public void setLastDrop_count(int lastDrop_count) {
        this.lastDrop_count = lastDrop_count;
    }

    public Date getLastDrop_date() {
        return lastDrop_date;
    }

    public void setLastDrop_date(Date lastDrop_date) {
        this.lastDrop_date = lastDrop_date;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
