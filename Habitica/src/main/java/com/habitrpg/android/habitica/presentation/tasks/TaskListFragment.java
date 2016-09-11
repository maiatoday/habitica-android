package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.presentation.base.BaseFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TaskListFragment extends BaseFragment<List<Task>, TaskListView, TaskListPresenter> implements TaskListView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public String taskType;
    public TasksRecyclerViewAdapter recyclerAdapter;

    @Override
    public void injectFragment(AppComponent component) {
        component.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
        }

        this.presenter.taskType = this.taskType;

        return rootView;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(List<Task> tasks) {
        if (recyclerAdapter == null) {
            recyclerAdapter = new TasksRecyclerViewAdapter(getContext(), tasks, true, 0);
        }
        recyclerView.setAdapter(recyclerAdapter);
        showContent();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (pullToRefresh) {
            this.presenter.reload();
        } else {
            this.presenter.loadTaskList();
        }
    }
}
