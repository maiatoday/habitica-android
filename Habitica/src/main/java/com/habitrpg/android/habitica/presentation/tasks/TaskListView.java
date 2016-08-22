package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.presentation.base.BaseView;

import java.util.List;

public interface TaskListView extends BaseView {

    void showTasks(List<Task> tasks);
}
