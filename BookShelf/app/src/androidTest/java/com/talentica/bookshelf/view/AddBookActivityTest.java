package com.talentica.bookshelf.view;


import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.addmybook.view.activity.AddMyBookActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class AddBookActivityTest {
    @Rule
    public ActivityTestRule<AddMyBookActivity> addBookActivityTestRule = new ActivityTestRule<AddMyBookActivity>(AddMyBookActivity.class);


    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = addBookActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(addBookActivityTestRule.getActivity().getResources().getString(R.string.add_book_text)));

    }


    @Test
    public void containsFragmentManuallyFillTest() {

        Fragment fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookMainFragment"));

        onView(withId(R.id.enter_detail_button)).perform(click());
        fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookDetailFragment"));
        onView(withId(R.id.back)).perform(click());

        fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookMainFragment"));

    }


    @Test
    public void containsFragmentAutoFillTest() {

        Fragment fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookMainFragment"));

        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                onChildView(withId(R.id.book_thumbnail_image)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.next)).perform(click());


        fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookDetailFragment"));
        onView(withId(R.id.back)).perform(click());

        fragment = addBookActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.add_book_activity_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("AddBookMainFragment"));

    }


}
