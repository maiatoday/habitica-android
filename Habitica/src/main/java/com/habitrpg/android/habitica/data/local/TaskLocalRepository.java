package com.habitrpg.android.habitica.data.local;


import com.habitrpg.android.habitica.models.Task;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public interface TaskLocalRepository extends BaseLocalRepository {

    Observable<RealmResults<Task>> getTasks(String taskType);

    void saveTasks(List<Task> tasks);
}
