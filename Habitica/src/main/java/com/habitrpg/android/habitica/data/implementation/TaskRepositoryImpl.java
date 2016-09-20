package com.habitrpg.android.habitica.data.implementation;

import com.habitrpg.android.habitica.data.TaskRepository;
import com.habitrpg.android.habitica.data.local.TaskLocalRepository;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.network.ApiClient;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public class TaskRepositoryImpl extends BaseRepositoryImpl<TaskLocalRepository> implements TaskRepository {


    public TaskRepositoryImpl(TaskLocalRepository localRepository, ApiClient apiClient) {
        super(localRepository, apiClient);
    }

    @Override
    public Observable<RealmResults<Task>> getTasks(String taskType) {
        return this.localRepository.getTasks(taskType);
    }

    @Override
    public Observable<List<Task>> refreshTasks() {
        return this.apiClient.getUserTasks()
                .doOnNext(this.localRepository::saveTasks);
    }
}
