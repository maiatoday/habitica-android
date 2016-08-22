package com.habitrpg.android.habitica.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Days implements RealmModel {

    @PrimaryKey
    private String taskId;

    private boolean m, t,w, th,f,s,su;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean getM() {
        return m;
    }

    public void setM(boolean m) {
        this.m = m;
    }

    public boolean getT() {
        return t;
    }

    public void setT(boolean t) {
        this.t = t;
    }

    public boolean getW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public boolean getTh() {
        return th;
    }

    public void setTh(boolean th) {
        this.th = th;
    }

    public boolean getF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public boolean getS() {
        return s;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public boolean getSu() {
        return su;
    }

    public void setSu(boolean su) {
        this.su = su;
    }

    public boolean getForDay(int day) {
        switch (day) {
            case 2: return this.getM();
            case 3: return this.getT();
            case 4: return this.getW();
            case 5: return this.getTh();
            case 6: return this.getF();
            case 7: return this.getS();
            case 1: return this.getSu();
        }
        return false;
    }
}
