package com.habitrpg.android.habitica.data;


import com.habitrpg.android.habitica.data.local.BaseLocalRepository;
import com.habitrpg.android.habitica.models.Task;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public interface TaskRepository extends BaseLocalRepository {

    Observable<RealmResults<Task>> getTasks(String taskType);

    Observable<List<Task>> refreshTasks();
}
