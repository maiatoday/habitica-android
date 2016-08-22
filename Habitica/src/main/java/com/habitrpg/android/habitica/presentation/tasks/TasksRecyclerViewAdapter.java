package com.habitrpg.android.habitica.presentation.tasks;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.models.Task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TasksRecyclerViewAdapter extends RealmRecyclerViewAdapter<Task, TasksRecyclerViewAdapter.TaskAdapter> {

    public TasksRecyclerViewAdapter(Context context, List<Task> data, boolean autoUpdate) {
        super(context, (OrderedRealmCollection<Task>) data, autoUpdate);
    }

    @Override
    public TaskAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskAdapter holder, int position) {
        holder.setTask(getData().get(position));
    }

    public static class TaskAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        public TextView textView;
        @BindView(R.id.notesTextView)
        public TextView notesTextView;
        @BindView(R.id.valueTextView)
        public TextView valueTextView;

        public TaskAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTask(Task task) {
            textView.setText(task.getText());
            notesTextView.setText(task.getNotes());
            valueTextView.setText(String.valueOf(task.getValue()));
        }

    }
}
