package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.interactors.ListTasksUseCase;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.presentation.base.BasePresenter;
import com.habitrpg.android.habitica.repositories.TaskRepository;
import com.habitrpg.android.habitica.repositories.implementations.RealmTaskRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class TaskListPresenter extends BasePresenter<TaskListView> {

    private final PostExecutionThread postExecutionThread;
    private TaskRepository repository;
    private ThreadExecutor threadExecutor;

    @Inject
    public TaskListPresenter(TaskRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.repository = repository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    private ListTasksUseCase getListTaskuseCase() {
        return new ListTasksUseCase(this.repository, this.threadExecutor, this.postExecutionThread);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        super.destroy();
        this.getListTaskuseCase().unsubscribe();
    }

    public void loadTaskList() {
        this.getListTaskuseCase().execute(new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Task> tasks) {
                if (getView() != null) {
                    getView().showTasks(tasks);
                }
            }
        });
    }
}
