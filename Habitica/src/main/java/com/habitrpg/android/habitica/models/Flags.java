package com.habitrpg.android.habitica.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Flags implements RealmModel {

    @PrimaryKey
    String user_id;
    //RealmList<TutorialStep> tutorial;
    private boolean showTour, dropsEnabled, itemsEnabled, newStuff, classSelected, rebirthEnabled, welcomed, armoireEnabled, armoireOpened, armoireEmpty;

    //public void setTutorial(RealmList<TutorialStep> tutorial) {
    //    this.tutorial = tutorial;
    //}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean getShowTour() {
        return showTour;
    }

    public void setShowTour(boolean showTour) {
        this.showTour = showTour;
    }

    public boolean getDropsEnabled() {
        return dropsEnabled;
    }

    public void setDropsEnabled(boolean dropsEnabled) {
        this.dropsEnabled = dropsEnabled;
    }

    public boolean getItemsEnabled() {
        return itemsEnabled;
    }

    public void setItemsEnabled(boolean itemsEnabled) {
        this.itemsEnabled = itemsEnabled;
    }

    public boolean getNewStuff() {
        return newStuff;
    }

    public void setNewStuff(boolean newStuff) {
        this.newStuff = newStuff;
    }

    public boolean getClassSelected() {
        return classSelected;
    }

    public void setClassSelected(boolean classSelected) {
        this.classSelected = classSelected;
    }

    public boolean getRebirthEnabled() {
        return rebirthEnabled;
    }

    public void setRebirthEnabled(boolean rebirthEnabled) {
        this.rebirthEnabled = rebirthEnabled;
    }

    public boolean getWelcomed() {
        return welcomed;
    }

    public void setWelcomed(boolean welcomed) {
        this.welcomed = welcomed;
    }

    public boolean getArmoireEnabled() {
        return armoireEnabled;
    }

    public void setArmoireEnabled(boolean armoireEnabled) {
        this.armoireEnabled = armoireEnabled;
    }

    public boolean getArmoireOpened() {
        return armoireOpened;
    }

    public void setArmoireOpened(boolean armoireOpened) {
        this.armoireOpened = armoireOpened;
    }

    public boolean getArmoireEmpty() {
        return armoireEmpty;
    }

    public void setArmoireEmpty(boolean armoireEmpty) {
        this.armoireEmpty = armoireEmpty;
    }
}
