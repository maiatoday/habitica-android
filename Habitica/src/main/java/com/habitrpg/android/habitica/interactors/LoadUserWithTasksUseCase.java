package com.habitrpg.android.habitica.interactors;

import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.models.User;
import com.habitrpg.android.habitica.network.ApiClient;

import java.util.List;

import rx.Observable;

public class LoadUserWithTasksUseCase extends UseCase {

    private ApiClient client;

    public LoadUserWithTasksUseCase(ApiClient client, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.client = client;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable<User> userObservable = this.client.getUser();
            Observable<List<Task>> tasksObservable = this.client.getUserTasks();
        return Observable.merge(userObservable, tasksObservable);
    }
}
