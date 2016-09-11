package com.habitrpg.android.habitica.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class Stats implements RealmModel {

    @PrimaryKey
    String id;
    public Float con, str, per;
    @SerializedName("int")
    public Float _int;
    public BasicStats training;
    public BasicStats buffs;
    public Integer points, lvl;
    public Double gp, exp, mp, hp;
    private Integer toNextLevel, maxHealth, maxMP;
    @SerializedName("class")
    public String _class;



    public Integer getToNextLevel() {
        return toNextLevel != null ? toNextLevel : Integer.valueOf(0);
    }

    public void setToNextLevel(Integer toNextLevel) {
        if (toNextLevel != 0) {
            this.toNextLevel = toNextLevel;
        }
    }

    public Integer getMaxHealth() {
        return maxHealth != null ? maxHealth : Integer.valueOf(50);
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getMaxMP() {
        return maxMP != null ? maxMP : Integer.valueOf(0);
    }

    public void setMaxMP(Integer maxMP) {
        if (maxMP != 0) {
            this.maxMP = maxMP;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getCon() {
        return con;
    }

    public void setCon(Float con) {
        this.con = con;
    }

    public Float getStr() {
        return str;
    }

    public void setStr(Float str) {
        this.str = str;
    }

    public Float getPer() {
        return per;
    }

    public void setPer(Float per) {
        this.per = per;
    }

    public Float get_int() {
        return _int;
    }

    public void set_int(Float _int) {
        this._int = _int;
    }

    public BasicStats getTraining() {
        return training;
    }

    public void setTraining(BasicStats training) {
        this.training = training;
    }

    public BasicStats getBuffs() {
        return buffs;
    }

    public void setBuffs(BasicStats buffs) {
        this.buffs = buffs;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Double getGp() {
        return gp;
    }

    public void setGp(Double gp) {
        this.gp = gp;
    }

    public Double getExp() {
        return exp;
    }

    public void setExp(Double exp) {
        this.exp = exp;
    }

    public Double getMp() {
        return mp;
    }

    public void setMp(Double mp) {
        this.mp = mp;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public HabitClass get_class() {
        return (_class != null) ? HabitClass.valueOf(_class) : null;
    }

    public void set_class(HabitClass _class) {
        this._class = _class.toString();
    }
}
