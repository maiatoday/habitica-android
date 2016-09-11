package com.habitrpg.android.habitica.models;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class TutorialStep implements RealmModel {


    public String user_id;
    @PrimaryKey
    private String key;
    private String tutorialGroup, identifier;
    private boolean wasCompleted;
    private Date displayedOn;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String group) {
        this.tutorialGroup = group;
        this.key = group + "_" + this.identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        this.key = this.tutorialGroup + "_" + identifier;
    }

    public boolean getWasCompleted() {
        return wasCompleted;
    }

    public void setWasCompleted(boolean wasCompleted) {
        this.wasCompleted = wasCompleted;
    }

    public Date getDisplayedOn() {
        return displayedOn;
    }

    public void setDisplayedOn(Date displayedOn) {
        this.displayedOn = displayedOn;
    }
}
