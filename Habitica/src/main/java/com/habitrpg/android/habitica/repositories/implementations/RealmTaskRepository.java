package com.habitrpg.android.habitica.repositories.implementations;

import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.network.ApiClient;
import com.habitrpg.android.habitica.repositories.TaskRepository;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class RealmTaskRepository extends RealmBaseRepository implements TaskRepository {

    public RealmTaskRepository(Realm realm) {
        super(realm);
    }

    public Observable<?> getTasks(String taskType) {
        return realm.where(Task.class)
                .equalTo("type", taskType)
                .findAllSorted("position")
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}
