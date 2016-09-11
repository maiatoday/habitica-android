package com.habitrpg.android.habitica.modules;

import com.habitrpg.android.habitica.old.HostConfig;
import com.habitrpg.android.habitica.repositories.TaskRepository;
import com.habitrpg.android.habitica.repositories.UserRepository;
import com.habitrpg.android.habitica.repositories.implementations.RealmTaskRepository;
import com.habitrpg.android.habitica.repositories.implementations.RealmUserRepository;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RepositoryModule {

    @Provides
    TaskRepository providesTaskRepository(Realm realm) {
        return new RealmTaskRepository(realm);
    }

    @Provides
    UserRepository providesUserRepository(Realm realm, HostConfig config) {
        return new RealmUserRepository(realm ,config);
    }
}
