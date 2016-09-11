package com.habitrpg.android.habitica.interactors;

import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.repositories.TaskRepository;
import com.habitrpg.android.habitica.repositories.implementations.RealmTaskRepository;

import javax.inject.Inject;

import rx.Observable;

public class ListTasksUseCase extends UseCase {

    private final TaskRepository taskRepository;
    public String taskType;

    @Inject
    public ListTasksUseCase(TaskRepository taskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, String taskType) {
        super(threadExecutor, postExecutionThread);
        this.taskRepository = taskRepository;
        this.taskType = taskType;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return taskRepository.getTasks(this.taskType);
    }
}
