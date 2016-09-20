package com.habitrpg.android.habitica.data.local;

import com.habitrpg.android.habitica.models.User;

import rx.Observable;

public interface UserLocalRepository extends BaseLocalRepository {

    public Observable<User> getActiveUser();

    void saveUser(User user);
}
