package com.habitrpg.android.habitica.presentation.header;

import android.content.Context;

import com.habitrpg.android.habitica.presentation.base.BasePresenter;
import com.habitrpg.android.habitica.data.UserRepository;

import javax.inject.Inject;


public class HeaderPresenter extends BasePresenter<HeaderViewInterface> {

    private final UserRepository repository;
    private HeaderUserViewModel viewModel;

    @Inject
    public HeaderPresenter(UserRepository repository, Context context) {
        this.repository = repository;
        this.viewModel = new HeaderUserViewModel(context);
    }

    public void loadUser() {
        this.repository.getActiveUser()
                .map(user1 -> viewModel.setUser(user1))
                .subscribe(viewModel -> {
            if (getView() != null) {
                getView().setData(viewModel);
            }
        });
    }
}
