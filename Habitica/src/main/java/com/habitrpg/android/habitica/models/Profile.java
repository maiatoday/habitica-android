package com.habitrpg.android.habitica.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Profile implements RealmModel {

    @PrimaryKey
    String user_Id;
    private String name;
    private String blurb, imageUrl;

    public Profile(String name) {
        this(name, "", "");
    }

    public Profile(String name, String blurb, String imageUrl) {
        this.name = name;
        this.blurb = blurb;
        this.imageUrl = imageUrl;
    }

    public Profile() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
