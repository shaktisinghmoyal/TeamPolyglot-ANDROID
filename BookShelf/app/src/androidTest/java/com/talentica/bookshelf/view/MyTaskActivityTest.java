package com.talentica.bookshelf.view;


import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.tasks.view.activity.MyTaskActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MyTaskActivityTest {
    @Rule
    public ActivityTestRule<MyTaskActivity> myTaskActivityTestRule = new ActivityTestRule<MyTaskActivity>(MyTaskActivity.class);


    @Test
    public void containsFragmentTest() {
        Fragment fragment = myTaskActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.my_tasks_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("MyTaskFragment"));
    }

}
