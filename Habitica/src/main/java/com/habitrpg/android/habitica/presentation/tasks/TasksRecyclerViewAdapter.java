package com.habitrpg.android.habitica.presentation.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.BaseTaskViewHolder;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.DailyViewHolder;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.HabitViewHolder;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.RewardViewHolder;
import com.habitrpg.android.habitica.presentation.tasks.viewHolders.TodoViewHolder;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TasksRecyclerViewAdapter extends RealmRecyclerViewAdapter<Task, BaseTaskViewHolder> {

    private static final int VIEWTYPE_HABIT = 0;
    private static final int VIEWTYPE_DAILIES = 1;
    private static final int VIEWTYPE_TODOS = 2;
    private static final int VIEWTYPE_REWARDS = 3;
    private final int dailyOffset;

    public TasksRecyclerViewAdapter(Context context, List<Task> data, boolean autoUpdate, int dailyOffset) {
        super(context, (OrderedRealmCollection<Task>) data, autoUpdate);
        this.dailyOffset = dailyOffset;
    }

    @Override
    public BaseTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResource(viewType), parent, false);
        switch (viewType) {
            case VIEWTYPE_HABIT:
                return new HabitViewHolder(view);
            case VIEWTYPE_DAILIES:
                return new DailyViewHolder(view, dailyOffset);
            case VIEWTYPE_TODOS:
                return new TodoViewHolder(view);
            case VIEWTYPE_REWARDS:
                return new RewardViewHolder(view);
            default:
                return null;
        }
    }

    private int getLayoutResource(int viewType) {
        switch (viewType) {
            case VIEWTYPE_HABIT:
                return R.layout.habit_item_card;
            case VIEWTYPE_DAILIES:
                return R.layout.daily_item_card;
            case VIEWTYPE_TODOS:
                return R.layout.todo_item_card;
            case VIEWTYPE_REWARDS:
                return R.layout.reward_item_card;
            default:
                return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getData() != null) {
            Task task = getData().get(position);
            switch (task.getType()) {
                case Task.TYPE_HABIT:
                    return VIEWTYPE_HABIT;
                case Task.TYPE_DAILY:
                    return VIEWTYPE_DAILIES;
                case Task.TYPE_TODO:
                    return VIEWTYPE_TODOS;
                case Task.TYPE_REWARD:
                    return VIEWTYPE_REWARDS;
            }
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(BaseTaskViewHolder holder, int position) {
        if (getData() != null) {
            holder.bindHolder(getData().get(position), position);
        }
    }
}
