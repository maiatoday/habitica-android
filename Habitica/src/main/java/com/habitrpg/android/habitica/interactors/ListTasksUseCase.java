package com.habitrpg.android.habitica.interactors;

import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.data.TaskRepository;
import com.habitrpg.android.habitica.models.Task;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;
import rx.Observable;

public class ListTasksUseCase extends UseCase<ListTasksUseCase.RequestValues, RealmResults<Task>> {

    private final TaskRepository taskRepository;

    @Inject
    public ListTasksUseCase(TaskRepository taskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.taskRepository = taskRepository;
    }

    @Override
    protected Observable<RealmResults<Task>> buildUseCaseObservable(ListTasksUseCase.RequestValues requestValues) {
        return taskRepository.getTasks(requestValues.taskType);
    }

    public static final class RequestValues implements UseCase.RequestValues {

        protected final String taskType;

        public RequestValues(String taskType) {
            this.taskType = taskType;
        }
    }
}
