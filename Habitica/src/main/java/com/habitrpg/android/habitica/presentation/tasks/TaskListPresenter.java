package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.interactors.ListTasksUseCase;
import com.habitrpg.android.habitica.interactors.LoadUserWithTasksUseCase;
import com.habitrpg.android.habitica.presentation.base.BasePresenter;

import javax.inject.Inject;

public class TaskListPresenter extends BasePresenter<TaskListView> {

    private final LoadUserWithTasksUseCase loadUserWithTasksUseCase;
    private final ListTasksUseCase listTasksUseCase;
    public String taskType;

    @Inject
    public TaskListPresenter(ListTasksUseCase listTasksUseCase, LoadUserWithTasksUseCase loadUserWithTasksUseCase) {
        this.listTasksUseCase = listTasksUseCase;
        this.loadUserWithTasksUseCase = loadUserWithTasksUseCase;
    }

    public void loadTaskList() {
        if (getView() != null) {
            getView().showLoading(false);
        }

        this.getSubscriptions().add(this.listTasksUseCase.observable(new ListTasksUseCase.RequestValues(this.taskType))
        .subscribe(tasks -> {
            if (getView() != null) {
                getView().setData(tasks);
                getView().showContent();
            }
        }, this::handleError));
    }

    public void refresh() {
        this.getSubscriptions().add(this.loadUserWithTasksUseCase.observable(null).subscribe(aVoid -> {
            if (this.getView() != null) {
                getView().showContent();
            }
        }, this::handleError));
    }

}
