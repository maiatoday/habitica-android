package com.habitrpg.android.habitica.presentation.base;

public abstract class BasePresenter<V extends BaseView> {

    private V view;

    public abstract void resume();

    public abstract void pause();

    public void destroy() {
        this.view = null;
    };

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return this.view;
    };

}
