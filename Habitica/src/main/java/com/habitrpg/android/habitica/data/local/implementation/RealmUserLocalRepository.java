package com.habitrpg.android.habitica.data.local.implementation;

import com.habitrpg.android.habitica.data.local.UserLocalRepository;
import com.habitrpg.android.habitica.models.User;
import com.habitrpg.android.habitica.old.HostConfig;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;

public class RealmUserLocalRepository extends RealmBaseLocalRepository implements UserLocalRepository {

    private final HostConfig config;

    public RealmUserLocalRepository(Realm realm, HostConfig config) {
        super(realm);
        this.config = config;
    }

    @Override
    public Observable<User> getActiveUser() {
        return RealmObject.asObservable(
                realm.where(User.class)
                        .equalTo("id", this.config.getUser())
                        .findFirstAsync())
                        .filter(RealmObject::isLoaded)
                        .filter(RealmObject::isValid);
    }

    @Override
    public void saveUser(User user) {
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(user));
    }
}
