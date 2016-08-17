package com.talentica.bookshelf.view;


import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class SearchFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void listView1ItemClickTest() {

        onView(withId(R.id.search)).perform(click(), closeSoftKeyboard());
        onData(anything()).inAdapterView(withId(R.id.recent_search)).atPosition(0).perform(click());
        Fragment booksGridViewFragment =
                activityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);

        assertThat(booksGridViewFragment.getClass().getSimpleName(), is("BooksGridViewFragment"));

    }

    @Test
    public void listView2ItemClickTest() {

        onView(withId(R.id.search)).perform(click(), closeSoftKeyboard());
        onData(anything()).inAdapterView(withId(R.id.top_search)).atPosition(0).perform(click());
        Fragment booksGridViewFragment =
                activityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);

        assertThat(booksGridViewFragment.getClass().getSimpleName(), is("BooksGridViewFragment"));

    }

}
