package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.presentation.base.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TaskListFragment extends BaseFragment<TaskListPresenter> implements TaskListView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    public TaskListPresenter presenter;
    public String taskType;
    public TasksRecyclerViewAdapter recyclerAdapter;

    @Override
    public void injectFragment(AppComponent component) {
        component.inject(this);
    }

    public TaskListPresenter getPresenter() {
        return this.presenter;
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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            this.loadTaskList();
        }
    }

    private void loadTaskList() {
        this.presenter.loadTaskList();
    }

    @Override
    public void onRefresh() {
        this.getPresenter().loadTaskList();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        if (recyclerAdapter == null) {
            recyclerAdapter = new TasksRecyclerViewAdapter(getContext(), tasks, true);
        }
        recyclerView.setAdapter(recyclerAdapter);
        showContent();
    }
}
