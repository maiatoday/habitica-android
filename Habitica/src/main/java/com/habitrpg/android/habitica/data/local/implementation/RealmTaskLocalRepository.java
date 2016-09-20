package com.habitrpg.android.habitica.data.local.implementation;

import com.habitrpg.android.habitica.data.local.TaskLocalRepository;
import com.habitrpg.android.habitica.models.Task;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class RealmTaskLocalRepository extends RealmBaseLocalRepository implements TaskLocalRepository {

    public RealmTaskLocalRepository(Realm realm) {
        super(realm);
    }

    public Observable<RealmResults<Task>> getTasks(String taskType) {
        return realm.where(Task.class)
                .equalTo("type", taskType)
                .findAllSorted("position")
                .asObservable()
                .filter(RealmResults::isLoaded);
    }

    @Override
    public void saveTasks(List<Task> tasks) {
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(tasks));
    }
}
