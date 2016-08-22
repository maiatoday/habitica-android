package com.habitrpg.android.habitica.repositories;


import rx.Observable;

public interface TaskRepository {

    Observable<?> getTasks(String taskType);
}
