package com.habitrpg.android.habitica.repositories.implementations;

import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.repositories.TaskRepository;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class RealmTaskRepository implements TaskRepository {

    private final Realm realm;

    public RealmTaskRepository(Realm realm) {
        this.realm = realm;
    }

    public Observable<?> getTasks(String taskType) {
        return realm.where(Task.class)
                .equalTo("type", taskType)
                .findAllSorted("position")
                .asObservable();
    }
}
