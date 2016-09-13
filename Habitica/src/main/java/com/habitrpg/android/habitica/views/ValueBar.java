package com.habitrpg.android.habitica.views;

import com.habitrpg.android.habitica.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @BindView(R.id.bar_full)
    public View barFull;

    @BindView(R.id.progress_bar)
    public View bar;

    @BindView(R.id.progress_bar_remaining)
    public View barRemaining;

    public ValueBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.labeled_bar, this);

        ButterKnife.bind(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ValueBar,
                0, 0);

        descriptionTextView.setText(a.getString(R.styleable.ValueBar_descriptionText));

        Drawable iconRes = a.getDrawable(R.styleable.ValueBar_iconRes);
        if (iconRes != null) {
            iconImageView.setImageDrawable(iconRes);
        }

        valueTextView.setText(a.getString(R.styleable.ValueBar_valueText));

        int barColor = a.getColor(R.styleable.ValueBar_barColor, 0);
        if (barColor != 0) {
            Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.layout_rounded_bg, null);
            if (drawable != null) {
                drawable.setColorFilter(barColor, PorterDuff.Mode.MULTIPLY);
            }
            if (Build.VERSION.SDK_INT < 16) {
                bar.setBackgroundDrawable(drawable);
            } else {
                bar.setBackground(drawable);
            }
        }

        int backgroundColor = a.getColor(R.styleable.ValueBar_barBackgroundColor, 0);
        if (backgroundColor != 0) {
            barFull.setBackgroundColor(backgroundColor);
        }
        Drawable barBackground = a.getDrawable(R.styleable.ValueBar_barBackground);
        if (barBackground != null) {
            if (Build.VERSION.SDK_INT < 16) {
                barFull.setBackgroundDrawable(barBackground);
            } else {
                barFull.setBackground(barBackground);
            }
        }

        int textColor = a.getColor(R.styleable.ValueBar_labelTextColor, 0);
        if (textColor != 0) {
            valueTextView.setTextColor(textColor);
            descriptionTextView.setTextColor(textColor);
        }

        a.recycle();
    }

    public void setPercentage(float percentage) {
        bar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f-percentage));
        barRemaining.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, percentage));
    }

    public void setValueText(String valueText) {
        valueTextView.setText(valueText);
    }
}
