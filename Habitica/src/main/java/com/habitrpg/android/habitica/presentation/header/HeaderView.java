package com.habitrpg.android.habitica.presentation.header;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.models.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends FrameLayout implements HeaderViewInterface {

    @Inject
    HeaderPresenter presenter;

    @BindView(R.id.lvl_tv)
    TextView levelTextView;

    @BindView(R.id.gems_tv)
    TextView gemsTextView;

    @BindView(R.id.gold_tv)
    TextView goldTextView;

    @BindView(R.id.silver_tv)
    TextView silverTextView;

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.header_view, this);

        ButterKnife.bind(this);
    }

    public void setUp(AppComponent component) {
        component.inject(this);
        this.presenter.attachView(this);
        this.loadData(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(HeaderUserViewModel viewModel) {
        levelTextView.setCompoundDrawables(viewModel.classDrawable, null, null, null);
        levelTextView.setText(viewModel.levelString);
        gemsTextView.setText(viewModel.gems);
        goldTextView.setText(viewModel.gold);
        silverTextView.setText(viewModel.silver);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        this.presenter.loadUser();
    }
}
