package com.habitrpg.android.habitica.presentation.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String errorText);

    void showContent();

}
