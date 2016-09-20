package com.habitrpg.android.habitica.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.presentation.tasks.TaskListPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<M, V extends BaseView<M>, P extends BasePresenter<V>> extends MvpLceFragment<SwipeRefreshLayout, M, V, P>
        implements BaseView<M>, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    public P presenter;

    private Unbinder unbinder;

    @NonNull
    @Override
    public P createPresenter() {
        return this.presenter;
    }

    @NonNull
    public P getPresenter() {
        return this.presenter;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.injectFragment(getComponent());
    }

    @Override
    public void onStop() {
        this.presenter.stop();
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);

        FrameLayout contentContainer = ButterKnife.findById(rootView, R.id.contentContainer);
        inflater.inflate(getLayoutID(), contentContainer, true);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentView.setOnRefreshListener(this);
        loadData(false);
    }

    @Override
    public void showContent() {
        super.showContent();
        if (contentView.isRefreshing()) {
            contentView.setRefreshing(false);
        }
    }

    abstract public int getLayoutID();

    protected abstract void injectFragment(AppComponent component);

    public AppComponent getComponent() {
        return ((BaseActivity) getActivity()).getHabiticaApplication().getComponent();
    }

    @Override public void onRefresh() {
        loadData(true);
    }
}
