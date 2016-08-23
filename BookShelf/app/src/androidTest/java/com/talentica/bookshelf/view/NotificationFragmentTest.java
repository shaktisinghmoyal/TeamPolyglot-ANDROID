package com.talentica.bookshelf.view;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBar;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.notifications.view.activity.NotificationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class NotificationFragmentTest {
    final AtomicReference<String> bookName = new AtomicReference<String>();
    final AtomicReference<String> writer = new AtomicReference<String>();
    @Rule
    public ActivityTestRule<NotificationActivity> notificationActivityTestRule = new ActivityTestRule<NotificationActivity>(NotificationActivity.class);

    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = notificationActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), containsString(notificationActivityTestRule.getActivity().getResources().getString(R.string.notifications)));

    }

    @Test
    public void scrollingTest() {

        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(6));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(12));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(12));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(14));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(18));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.notification_list))
                .perform(RecyclerViewActions.scrollToPosition(18));
    }

}
