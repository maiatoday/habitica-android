package com.habitrpg.android.habitica.views;

import com.habitrpg.android.habitica.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

public class HeaderView extends FrameLayout {

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.header_view, this);

        ButterKnife.bind(this);
    }
}
