package com.habitrpg.android.habitica.views;

import com.habitrpg.android.habitica.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValueBar extends RelativeLayout {

    @BindView(R.id.TV_label)
    public TextView descriptionTextView;

    @BindView(R.id.TV_value)
    public TextView valueTextView;

    @BindView(R.id.ic_header)
    public ImageView iconImageView;

    @BindView(R.id.bar)
    public View bar;

    public ValueBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.labeled_bar, this);

        ButterKnife.bind(this);
    }
}
