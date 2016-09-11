package com.habitrpg.android.habitica.repositories.implementations;

import io.realm.Realm;

/**
 * Created by viirus on 11-Sep-16.
 */
public class RealmBaseRepository {

    protected final Realm realm;

    public RealmBaseRepository(Realm realm) {
        this.realm = realm;
    }
}
