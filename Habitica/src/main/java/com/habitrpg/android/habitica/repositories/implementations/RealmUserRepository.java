package com.habitrpg.android.habitica.repositories.implementations;

import com.habitrpg.android.habitica.models.User;
import com.habitrpg.android.habitica.old.HostConfig;
import com.habitrpg.android.habitica.repositories.UserRepository;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;

public class RealmUserRepository extends RealmBaseRepository implements UserRepository {

    private final HostConfig config;

    public RealmUserRepository(Realm realm, HostConfig config) {
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
}
