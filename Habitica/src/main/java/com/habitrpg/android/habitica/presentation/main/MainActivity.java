package com.habitrpg.android.habitica.presentation.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.habitrpg.android.habitica.HabiticaApplication;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.network.ApiClient;
import com.habitrpg.android.habitica.presentation.base.BaseActivity;
import com.habitrpg.android.habitica.presentation.header.HeaderView;
import com.habitrpg.android.habitica.presentation.tasks.TaskListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;

public class MainActivity extends BaseActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    public ViewPager mViewPager;

    @BindView(R.id.header_view)
    public HeaderView headerView;

    @Inject
    ApiClient apiClient;

    @Inject
    Realm realm;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!HabiticaApplication.checkUserAuthentication(this, apiClient.hostConfig)) {
            return;
        }

        this.headerView.setUp(getHabiticaApplication().getComponent());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.detail_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        apiClient.getUserTasks().subscribe(tasks -> {
            realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(tasks));
        }, throwable -> {});
        apiClient.getUser().subscribe(user -> {
            realm.executeTransactionAsync(realm1 -> {
                realm1.insertOrUpdate(user);
            });
        }, throwable -> {});
    }

    @Override
    protected void injectActivity(AppComponent component) {
        component.inject(this);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            TaskListFragment fragment = new TaskListFragment();

            switch (position) {
                case 0:
                    fragment.taskType = Task.TYPE_HABIT;
                    break;
                case 1:
                    fragment.taskType = Task.TYPE_DAILY;
                    break;
                case 2:
                    fragment.taskType = Task.TYPE_TODO;
                    break;
                case 3:
                    fragment.taskType = Task.TYPE_REWARD;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Habits";
                case 1:
                    return "Dailies";
                case 2:
                    return "To-Dos";
                case 3:
                    return "To-Dos";
            }
            return null;
        }
    }
}
