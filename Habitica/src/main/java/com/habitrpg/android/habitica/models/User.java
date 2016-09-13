package com.habitrpg.android.habitica.models;


import android.text.TextUtils;

import com.habitrpg.android.habitica.views.AvatarView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class User implements RealmModel {

    @PrimaryKey
    private String id;
    private double balance;
    private Stats stats;
    private Preferences preferences;
    private Profile profile;
    private Items items;
    private Flags flags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public List<String> getAvatarLayerNames() {
        List<String> layerNames = new ArrayList<String>();

        Preferences prefs = this.getPreferences();

        if (prefs.getChair() != null) {
            layerNames.add(prefs.getChair());
        }

        Outfit outfit = null;
        if (this.getItems() != null) {
            if (prefs.getCostume()) {
                outfit = this.getItems().getGear().getCostume();
            } else {
                outfit = this.getItems().getGear().getEquipped();
            }
        }

        if (outfit != null) {
            if (outfit.getBack() != null) {
                layerNames.add(outfit.getBack());
            }
        }

        if (prefs.getSleep()) {
            layerNames.add("skin_" + prefs.getSkin() + "_sleep");
        } else {
            layerNames.add("skin_" + prefs.getSkin());
        }
        layerNames.add(prefs.getSize() + "_shirt_" + prefs.getShirt());
        layerNames.add("head_0");

        if (outfit != null) {
            String armor = outfit.getArmor();

            if (armor != null && !armor.equals("armor_base_0")) {
                layerNames.add(prefs.getSize() + "_" + armor);
            }
            if (outfit.getBody() != null && !outfit.getBody().equals("body_base_0")) {
                layerNames.add(outfit.getBody());
            }
        }

        Hair hair = prefs.getHair();
        if (hair != null) {
            String hairColor = hair.getColor();

            if (hair.getBase() > 0) {
                layerNames.add("hair_base_" + hair.getBase() + "_" + hairColor);
            }
            if (hair.getBangs() > 0) {
                layerNames.add("hair_bangs_" + hair.getBangs() + "_" + hairColor);
            }
            if (hair.getMustache() > 0) {
                layerNames.add("hair_mustache_" + hair.getMustache() + "_" + hairColor);
            }
            if (hair.getBeard() > 0) {
                layerNames.add("hair_beard_" + hair.getBeard() + "_" + hairColor);
            }
        }

        if (outfit != null) {
            if (outfit.getEyeWear() != null && !outfit.getEyeWear().equals("eyewear_base_0")) {
                layerNames.add(outfit.getEyeWear());
            }
            if (outfit.getHead() != null && !outfit.getHead().equals("head_base_0")) {
                layerNames.add(outfit.getHead());
            }
            if (outfit.getHeadAccessory() != null && !outfit.getHeadAccessory().equals("headAccessory_base_0")) {
                layerNames.add(outfit.getHeadAccessory());
            }
            if (outfit.getShield() != null && !outfit.getShield().equals("shield_base_0")) {
                layerNames.add(outfit.getShield());
            }
            if (outfit.getWeapon() != null && !outfit.getWeapon().equals("weapon_base_0")) {
                layerNames.add(outfit.getWeapon());
            }
        }

        if (prefs.getSleep()) {
            layerNames.add("zzz");
        }

        return layerNames;
    }

    public EnumMap<AvatarView.LayerType, String> getAvatarLayerMap() {
        EnumMap<AvatarView.LayerType, String> layerMap = new EnumMap<>(AvatarView.LayerType.class);

        Preferences prefs = getPreferences();
        Outfit outfit = (prefs.getCostume()) ? getItems().getGear().getCostume() : getItems().getGear().getEquipped();

        if (!TextUtils.isEmpty(prefs.getChair())) {
            layerMap.put(AvatarView.LayerType.CHAIR, prefs.getChair());
        }

        if (outfit != null) {
            if (!TextUtils.isEmpty(outfit.getBack())) {
                layerMap.put(AvatarView.LayerType.BACK, outfit.getBack());
            }
            if (outfit.isAvailable(outfit.getArmor())) {
                layerMap.put(AvatarView.LayerType.ARMOR, prefs.getSize() + "_" + outfit.getArmor());
            }
            if (outfit.isAvailable(outfit.getBody())) {
                layerMap.put(AvatarView.LayerType.BODY, outfit.getBody());
            }
            if (outfit.isAvailable(outfit.getEyeWear())) {
                layerMap.put(AvatarView.LayerType.EYEWEAR, outfit.getEyeWear());
            }
            if (outfit.isAvailable(outfit.getHead())) {
                layerMap.put(AvatarView.LayerType.HEAD, outfit.getHead());
            }
            if (outfit.isAvailable(outfit.getHeadAccessory())) {
                layerMap.put(AvatarView.LayerType.HEAD_ACCESSORY, outfit.getHeadAccessory());
            }
            if (outfit.isAvailable(outfit.getShield())) {
                layerMap.put(AvatarView.LayerType.SHIELD, outfit.getShield());
            }
            if (outfit.isAvailable(outfit.getWeapon())) {
                layerMap.put(AvatarView.LayerType.WEAPON, outfit.getWeapon());
            }
        }

        layerMap.put(AvatarView.LayerType.SKIN, "skin_" + prefs.getSkin() + ((prefs.getSleep()) ? "_sleep" : ""));
        layerMap.put(AvatarView.LayerType.SHIRT, prefs.getSize() + "_shirt_" + prefs.getShirt());
        layerMap.put(AvatarView.LayerType.HEAD_0, "head_0");

        Hair hair = prefs.getHair();
        if (hair != null) {
            String hairColor = hair.getColor();

            if (hair.isAvailable(hair.getBase())) {
                layerMap.put(AvatarView.LayerType.HAIR_BASE, "hair_base_" + hair.getBase() + "_" + hairColor);
            }
            if (hair.isAvailable(hair.getBangs())) {
                layerMap.put(AvatarView.LayerType.HAIR_BANGS, "hair_bangs_" + hair.getBangs() + "_" + hairColor);
            }
            if (hair.isAvailable(hair.getMustache())) {
                layerMap.put(AvatarView.LayerType.HAIR_MUSTACHE, "hair_mustache_" + hair.getMustache() + "_" + hairColor);
            }
            if (hair.isAvailable(hair.getBeard())) {
                layerMap.put(AvatarView.LayerType.HAIR_BEARD, "hair_beard_" + hair.getBeard() + "_" + hairColor);
            }
            if (hair.isAvailable(hair.getFlower())) {
                layerMap.put(AvatarView.LayerType.HAIR_FLOWER, "hair_flower_" + hair.getFlower());
            }
        }

        if (prefs.getSleep()) {
            layerMap.put(AvatarView.LayerType.ZZZ, "zzz");
        }

        return layerMap;
    }
}
