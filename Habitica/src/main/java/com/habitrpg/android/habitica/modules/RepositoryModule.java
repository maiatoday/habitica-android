package com.habitrpg.android.habitica.modules;

import com.google.android.gms.common.api.Api;
import com.habitrpg.android.habitica.data.TaskRepository;
import com.habitrpg.android.habitica.data.UserRepository;
import com.habitrpg.android.habitica.data.implementation.TaskRepositoryImpl;
import com.habitrpg.android.habitica.data.implementation.UserRepositoryImpl;
import com.habitrpg.android.habitica.data.local.TaskLocalRepository;
import com.habitrpg.android.habitica.data.local.UserLocalRepository;
import com.habitrpg.android.habitica.network.ApiClient;
import com.habitrpg.android.habitica.old.HostConfig;
import com.habitrpg.android.habitica.data.local.implementation.RealmTaskLocalRepository;
import com.habitrpg.android.habitica.data.local.implementation.RealmUserLocalRepository;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RepositoryModule {

    @Provides
    TaskLocalRepository providesTaskLocalRepository(Realm realm) {
        return new RealmTaskLocalRepository(realm);
    }

    @Provides
    UserLocalRepository providesUserLocalRepository(Realm realm, HostConfig config) {
        return new RealmUserLocalRepository(realm ,config);
    }

    @Provides
    TaskRepository providesTaskRepository(TaskLocalRepository localRepository, ApiClient apiClient) {
        return new TaskRepositoryImpl(localRepository, apiClient);
    }

    @Provides
    UserRepository providesUserRepository(UserLocalRepository localRepository, ApiClient apiClient) {
        return new UserRepositoryImpl(localRepository, apiClient);
    }
}
