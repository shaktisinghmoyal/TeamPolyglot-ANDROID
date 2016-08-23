package com.talentica.bookshelf.view;


import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.notifications.view.activity.NotificationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class NotificationActivityTest {

    @Rule
    public ActivityTestRule<NotificationActivity> notificationActivityTestRule = new ActivityTestRule<NotificationActivity>(NotificationActivity.class);


    @Test
    public void containsFragmentTest() {
        Fragment fragment = notificationActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.notification_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("NotificationFragment"));
    }
}
