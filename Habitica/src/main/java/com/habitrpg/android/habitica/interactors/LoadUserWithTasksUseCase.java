package com.habitrpg.android.habitica.interactors;

import com.habitrpg.android.habitica.data.TaskRepository;
import com.habitrpg.android.habitica.data.UserRepository;
import com.habitrpg.android.habitica.domain.executors.PostExecutionThread;
import com.habitrpg.android.habitica.domain.executors.ThreadExecutor;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.models.User;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func2;

public class LoadUserWithTasksUseCase extends UseCase<LoadUserWithTasksUseCase.RequestValues, Void> {

    private final TaskRepository taskRepository;
    private UserRepository userRepository;

    @Inject
    public LoadUserWithTasksUseCase(UserRepository userRepository, TaskRepository taskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    protected Observable<Void> buildUseCaseObservable(LoadUserWithTasksUseCase.RequestValues requestValues) {
        Observable<User> userObservable = this.userRepository.refreshUser();
            Observable<List<Task>> tasksObservable = this.taskRepository.refreshTasks();
        return Observable.zip(userObservable, tasksObservable, (user, tasks) -> null);
    }

    public static final class RequestValues implements UseCase.RequestValues {}
}
