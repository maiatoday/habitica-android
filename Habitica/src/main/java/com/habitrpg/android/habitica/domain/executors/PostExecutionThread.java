package com.habitrpg.android.habitica.domain.executors;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
