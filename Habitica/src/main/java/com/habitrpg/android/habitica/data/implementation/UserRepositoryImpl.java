package com.habitrpg.android.habitica.data.implementation;

import com.habitrpg.android.habitica.data.UserRepository;
import com.habitrpg.android.habitica.data.local.UserLocalRepository;
import com.habitrpg.android.habitica.models.User;
import com.habitrpg.android.habitica.network.ApiClient;

import rx.Observable;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserLocalRepository> implements UserRepository {

    public UserRepositoryImpl(UserLocalRepository localRepository, ApiClient apiClient) {
        super(localRepository, apiClient);
    }


    @Override
    public Observable<User> getActiveUser() {
        return this.localRepository.getActiveUser();
    }

    @Override
    public Observable<User> refreshUser() {
        return apiClient.getUser()
                .doOnNext(this.localRepository::saveUser);
    }
}
