package com.habitrpg.android.habitica.presentation.tasks;

import android.util.Log;

import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.interactors.ListTasksUseCase;
import com.habitrpg.android.habitica.interactors.LoadUserWithTasksUseCase;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.network.ApiClient;
import com.habitrpg.android.habitica.presentation.base.BasePresenter;
import com.habitrpg.android.habitica.repositories.TaskRepository;
import com.habitrpg.android.habitica.repositories.implementations.RealmTaskRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class TaskListPresenter extends BasePresenter<TaskListView> {

    private final PostExecutionThread postExecutionThread;
    private final ApiClient client;
    private TaskRepository repository;
    private ThreadExecutor threadExecutor;
    public String taskType;

    @Inject
    public TaskListPresenter(TaskRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ApiClient client) {
        this.repository = repository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.client = client;
    }

    private ListTasksUseCase getListTaskuseCase() {
        return new ListTasksUseCase(this.repository, this.threadExecutor, this.postExecutionThread, this.taskType);
    }

    public void loadTaskList() {
        if (getView() != null) {
            getView().showLoading(false);
        }

        this.getListTaskuseCase().execute(new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {
                Log.e(this.getClass().getName(), "Completed");
            }

            @Override
            public void onError(Throwable e) {
                if (getView() != null) {
                    getView().showError(e, false);
                }
            }

            @Override
            public void onNext(List<Task> tasks) {
                if (getView() != null) {
                    getView().setData(tasks);
                    getView().showContent();
                }
            }
        });
    }

    public void reload() {
        new LoadUserWithTasksUseCase(this.client, this.threadExecutor, this.postExecutionThread).execute(new Subscriber() {
            @Override
            public void onCompleted() {
                if (getView() != null) {
                    getView().showContent();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }
}
