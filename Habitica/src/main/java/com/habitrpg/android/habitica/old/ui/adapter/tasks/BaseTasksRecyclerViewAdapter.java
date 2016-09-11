package com.habitrpg.android.habitica.old.ui.adapter.tasks;

import com.crashlytics.android.Crashlytics;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.old.events.TaskCreatedEvent;
import com.habitrpg.android.habitica.old.events.TaskRemovedEvent;
import com.habitrpg.android.habitica.old.events.TaskUpdatedEvent;
import com.habitrpg.android.habitica.old.events.commands.FilterTasksByTagsCommand;
import com.habitrpg.android.habitica.old.events.commands.TaskCheckedCommand;
import com.habitrpg.android.habitica.old.helpers.TagsHelper;
import com.habitrpg.android.habitica.old.ui.helpers.MarkdownParser;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.BaseTaskViewHolder;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BaseTasksRecyclerViewAdapter<VH extends BaseTaskViewHolder>
        extends RecyclerView.Adapter<VH> {

    private final String userID;
    int layoutResource;
    String taskType;
    Context context;
    List<Task> content;
    List<Task> filteredContent;
    private TagsHelper tagsHelper;

    public BaseTasksRecyclerViewAdapter(String taskType, TagsHelper tagsHelper, int layoutResource,
                                        Context newContext, String userID) {
        this.setHasStableIds(true);
        this.taskType = taskType;
        this.context = newContext;
        this.tagsHelper = tagsHelper;
        this.userID = userID;
        this.filteredContent = new ArrayList<>();

        this.layoutResource = layoutResource;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Task item = filteredContent.get(position);

        holder.bindHolder(item, position);

        /*if (this.displayedChecklist != null && ChecklistedViewHolder.class.isAssignableFrom(holder.getClass())) {
            ChecklistedViewHolder checklistedHolder = (ChecklistedViewHolder) holder;
            checklistedHolder.setDisplayChecklist(this.displayedChecklist == position);
        }*/
    }

    @Override
    public long getItemId(int position) {
        Task task = filteredContent.get(position);
        return task.getId().hashCode();
    }

    @Override
    public int getItemCount() {
        return filteredContent != null ? filteredContent.size() : 0;
    }

    public View getContentView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);

    }

    private void updateTask(Task task) {
        int i;
        for (i = 0; i < this.content.size(); ++i) {
            if (content.get(i).getId().equals(task.getId())) {
                break;
            }
        }
        if (i < content.size()) {
            content.set(i, task);
        }
    }


}
