package com.talentica.bookshelf.view;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.ListAllActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ListAllActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void viewAllRecentlyAddedTest() {
        onView(withId(R.id.recently_added_view_all_text)).perform(click());
        ListAllActivity activity = (ListAllActivity) getActivityInstance();
        assertThat(activity.getClass().getSimpleName(), is("ListAllActivity"));

        Fragment fragment = ((ListAllActivity) getActivityInstance()).getSupportFragmentManager().findFragmentById(R.id.book_grid_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("BooksGridViewFragment"));


    }

    @Test
    public void viewAllMostReadTest() {
        onView(withId(R.id.most_read_view_all_text)).perform(click());
        ListAllActivity activity = (ListAllActivity) getActivityInstance();
        assertThat(activity.getClass().getSimpleName(), is("ListAllActivity"));

        Fragment fragment = ((ListAllActivity) getActivityInstance()).getSupportFragmentManager().findFragmentById(R.id.book_grid_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("BooksGridViewFragment"));


    }


    public Activity getActivityInstance() {
        final Activity[] activity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity act : resumedActivities) {
                    activity[0] = act;
                    break;
                }
            }
        });

        return activity[0];
    }
}
