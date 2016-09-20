package com.habitrpg.android.habitica.data.local.implementation;

import com.habitrpg.android.habitica.data.local.BaseLocalRepository;

import io.realm.Realm;

public abstract class RealmBaseLocalRepository implements BaseLocalRepository {

    protected final Realm realm;

    public RealmBaseLocalRepository(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void close() {
        realm.close();
    }
}
