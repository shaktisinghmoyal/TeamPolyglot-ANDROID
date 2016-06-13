package com.talentica.bookshelf.view;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void drawerMenuTest() {
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withId(R.id.left_drawer)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.left_drawer)).atPosition(0).perform(click());
        onView(withId(R.id.left_drawer)).check(matches(not(isDisplayed())));

    }


    @Test
    public void bottomBarTest() {

        onView(withId(R.id.home_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

        onView(withId(R.id.todo_item)).perform(click());

        onView(withId(R.id.home_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

        onView(withId(R.id.add_book_item)).perform(click());

        onView(withId(R.id.home_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

        onView(withId(R.id.notification_item)).perform(click());

        onView(withId(R.id.home_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

        onView(withId(R.id.profile_item)).perform(click());

        onView(withId(R.id.home_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

        onView(withId(R.id.home_item)).perform(click());

        onView(withId(R.id.home_selected)).check(matches(isDisplayed()));
        onView(withId(R.id.home)).check(matches(isDisplayed()));
        onView(withId(R.id.todo_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.todo)).check(matches((isDisplayed())));
        onView(withId(R.id.add_book_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.add_book)).check(matches((isDisplayed())));
        onView(withId(R.id.notification_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.notification)).check(matches((isDisplayed())));
        onView(withId(R.id.profile_selected)).check(matches(not(isDisplayed())));
        onView(withId(R.id.profile)).check(matches((isDisplayed())));

    }

    @Test
    public void containsHomeFragmentTest() {
        Fragment bookListFragment =
                mainActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        assertThat(bookListFragment, is(notNullValue()));
    }


    @Test
    public void searchBarTest() {
        onView(withId(R.id.search)).perform(click());
        Fragment bookListFragment =
                mainActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);

        assertThat(bookListFragment.getClass().getSimpleName(), is("SearchFragment"));
        onView(withContentDescription("Collapse")).perform(click());
        Fragment homeFragment =
                mainActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        assertThat(homeFragment.getClass().getSimpleName(), is("HomeFragment"));


        onView(withId(R.id.search)).perform(click());
        pressBack();
        pressBack();
        assertThat(homeFragment.getClass().getSimpleName(), not("HomeFragment"));


        onView(withId(R.id.search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("android testing"));
        onView(withId(android.support.design.R.id.search_close_btn)).perform(click());
        pressBack();
        pressBack();
        assertThat(homeFragment.getClass().getSimpleName(), is("HomeFragment"));


    }


    @Test
    public void appBarTitleTest() {
        String actualTitle = mainActivityTestRule.getActivity().getSupportActionBar().getTitle().toString().trim();
        assertThat(actualTitle, is("Home"));
    }

//    private String getString(int resId){
//        return InstrumentationRegistry.getInstrumentation().getTargetContext().getString(resId);
//    }
}
