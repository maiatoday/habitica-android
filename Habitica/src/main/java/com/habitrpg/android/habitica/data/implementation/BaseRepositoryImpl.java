package com.habitrpg.android.habitica.data.implementation;

import com.habitrpg.android.habitica.data.BaseRepository;
import com.habitrpg.android.habitica.data.local.BaseLocalRepository;
import com.habitrpg.android.habitica.data.local.TaskLocalRepository;
import com.habitrpg.android.habitica.network.ApiClient;

public abstract class BaseRepositoryImpl<T extends BaseLocalRepository> implements BaseRepository {

    protected final T localRepository;
    protected final ApiClient apiClient;

    public BaseRepositoryImpl(T localRepository, ApiClient apiClient) {
        this.localRepository = localRepository;
        this.apiClient = apiClient;
    }

    @Override
    public void close() {
        this.localRepository.close();
    }
}
