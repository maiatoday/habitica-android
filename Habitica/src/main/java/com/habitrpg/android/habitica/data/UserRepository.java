package com.habitrpg.android.habitica.data;

import com.habitrpg.android.habitica.data.local.BaseLocalRepository;
import com.habitrpg.android.habitica.models.User;

import rx.Observable;


public interface UserRepository extends BaseLocalRepository {

    Observable<User> getActiveUser();

    Observable<User> refreshUser();
}
