package com.habitrpg.android.habitica.repositories;

import com.habitrpg.android.habitica.models.User;

import rx.Observable;

/**
 * Created by viirus on 11-Sep-16.
 */
public interface UserRepository {

    public Observable<User> getActiveUser();
}
