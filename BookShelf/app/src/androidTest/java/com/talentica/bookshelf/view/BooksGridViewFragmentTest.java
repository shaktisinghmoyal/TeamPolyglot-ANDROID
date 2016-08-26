package com.talentica.bookshelf.view;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v7.app.ActionBar;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.view.activity.ListAllActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BooksGridViewFragmentTest {

    final AtomicReference<String> bookName = new AtomicReference<String>();
    final AtomicReference<String> writer = new AtomicReference<String>();
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void actionBarRecentlyAddedTest() {
        onView(withId(R.id.recently_added_view_all_text)).perform(click());
        ListAllActivity activity = (ListAllActivity) getActivityInstance();
        ActionBar actionBar = activity.getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(activity.getResources().getString(R.string.recently_added_text)));
        onView(withId(R.id.fragment_title)).check(matches(withText(activity.getResources().getString(R.string.recently_added_text))));
    }

    @Test
    public void actionBarMostReadTest() {
        onView(withId(R.id.most_read_view_all_text)).perform(click());
        ListAllActivity activity = (ListAllActivity) getActivityInstance();
        ActionBar actionBar = activity.getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(activity.getResources().getString(R.string.most_read_text)));
        onView(withId(R.id.fragment_title)).check(matches(withText(activity.getResources().getString(R.string.most_read_text))));

    }

    @Test
    public void fragmentTitleWithElasticSearchTest() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("testing"));
        onView(withId(R.id.fragment_title)).check(matches(withText(activityTestRule.getActivity().getResources().getString(R.string.results_string))));

    }

    @Test
    public void bookDetailLaunchTest() {
        onView(withId(R.id.recently_added_view_all_text)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_name))));
        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_author))));
        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_lender))));

        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_name))));
        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_author))));
        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_lender))));

        onData(anything()).inAdapterView(withId(R.id.book_grid_view)).atPosition(6).perform(click());

        onView(withId(R.id.book_name_in_book_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.book_auther_in_book_layout)).check(matches(isDisplayed()));


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
