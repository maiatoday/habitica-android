package com.habitrpg.android.habitica.presentation.tasks.viewHolders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.models.Task;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseTaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Task task;
    protected Context context;
    @BindView(R.id.checkedTextView)
    TextView titleTextView;

    @BindView(R.id.notesTextView)
    TextView notesTextView;

    @Nullable
    @BindView(R.id.rightBorderView)
    View rightBorderView;

    @BindColor(R.color.task_gray)
    int taskGray;

    public BaseTaskViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        itemView.setClickable(true);

        ButterKnife.bind(this, itemView);

        //Re enable when we find a way to only react when a link is tapped.
        //this.notesTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //this.titleTextView.setMovementMethod(LinkMovementMethod.getInstance());

        context = itemView.getContext();
    }

    public void bindHolder(Task newTask, int position) {
        this.task = newTask;
        if (this.canContainMarkdown()) {
            if (this.task.parsedText != null) {
                this.titleTextView.setText(this.task.parsedText);
                this.notesTextView.setText(this.task.parsedNotes);
            } else {
                this.titleTextView.setText(this.task.getText());
                this.notesTextView.setText(this.task.getNotes());
            }
        } else {
            this.titleTextView.setText(this.task.getText());
            this.notesTextView.setText(this.task.getNotes());
        }
        if (this.task.getNotes() != null && this.task.getNotes().length() > 0) {
            this.notesTextView.setVisibility(View.VISIBLE);
        } else {
            this.notesTextView.setVisibility(View.GONE);
        }

        if (this.rightBorderView != null) {
            this.rightBorderView.setBackgroundResource(this.task.getLightTaskColor());
        }
    }

    @Override
    public void onClick(View v) {
    }

    public boolean canContainMarkdown() {
        return true;
    }
}
