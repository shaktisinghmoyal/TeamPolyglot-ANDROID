package com.talentica.bookshelf.view;


import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.BookDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BookDetailActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() {
        onView(withId(R.id.recycler_view_recently_added_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }


    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = ((BookDetailActivity) getActivityInstance()).getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(activityTestRule.getActivity().getResources().getString(R.string.book_detail_title)));

    }

    @Test
    public void isRequiredFragmentThereTest() {
        Fragment fragment = ((BookDetailActivity) getActivityInstance()).getSupportFragmentManager().findFragmentById(R.id.book_detail_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("BookDetailFragment"));
    }

    @Test
    public void bottomViewsTest() {

        onView(withId(R.id.request_button)).check(matches(isDisplayed()));
        onView(withId(R.id.hidden_panel)).check(matches(not(isDisplayed())));

        onView(withId(R.id.request_button)).perform(click());

        onView(withId(R.id.hidden_panel)).check(matches(isDisplayed()));
        onView(withId(R.id.request_button)).check(matches(not(isDisplayed())));

        onView(withId(R.id.no)).perform(click());

        onView(withId(R.id.request_button)).check(matches(isDisplayed()));
        onView(withId(R.id.hidden_panel)).check(matches(not(isDisplayed())));


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
