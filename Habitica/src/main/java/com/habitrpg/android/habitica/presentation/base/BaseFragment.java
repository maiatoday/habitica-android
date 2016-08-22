package com.habitrpg.android.habitica.presentation.base;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.views.LceAnimator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.contentView)
    SwipeRefreshLayout contentView;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.errorView)
    TextView errorView;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.injectFragment(getComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);

        FrameLayout contentContainer = ButterKnife.findById(rootView, R.id.contentContainer);
        inflater.inflate(getLayoutID(), contentContainer, true);

        unbinder = ButterKnife.bind(this, rootView);

        contentView.setOnRefreshListener(this);

        return rootView;
    }

    abstract public int getLayoutID();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        this.getPresenter().setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPresenter().resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getPresenter().pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getPresenter().destroy();
    }

    public abstract P getPresenter();

    protected abstract void injectFragment(AppComponent component);

    public AppComponent getComponent() {
        return ((BaseActivity) getActivity()).getHabiticaApplication().getComponent();
    }

    @Override
    public void showError(String errorText) {

    }

    @Override
    public void showContent() {
        animateContentViewIn();
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading() {

        animateLoadingViewIn();
    }

    @Override
    public void hideLoading() {

    }

    protected void animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView);
    }

    /**
     * Called to animate from loading view to content view
     */
    protected void animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView);
    }

    /**
     * The default behaviour is to display a toast message as light error (i.e. pull-to-refresh
     * error).
     * Override this method if you want to display the light error in another way (like crouton).
     */
    protected void showLightError(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Animates the error view in (instead of displaying content view / loading view)
     */
    protected void animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView);
    }
}
