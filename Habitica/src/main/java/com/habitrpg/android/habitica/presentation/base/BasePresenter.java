package com.habitrpg.android.habitica.presentation.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private CompositeSubscription subscriptions;

    protected CompositeSubscription getSubscriptions() {
        if (this.subscriptions != null) {
            return this.subscriptions;
        }
        this.subscriptions = new CompositeSubscription();
        return this.subscriptions;
    }

    public void stop() {
        if (this.subscriptions != null && this.subscriptions.hasSubscriptions()) {
            this.subscriptions.unsubscribe();
            this.subscriptions = null;
        }
    }

    protected void handleError(Throwable e) {
        if (getView() != null) {
            getView().showError(e, false);
        }
    }
}
