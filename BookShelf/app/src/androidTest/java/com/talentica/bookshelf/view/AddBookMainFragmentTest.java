package com.talentica.bookshelf.view;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class AddBookMainFragmentTest {

    @Rule
    public ActivityTestRule<AddMyBookActivity> addBookActivityTestRule = new ActivityTestRule<AddMyBookActivity>(AddMyBookActivity.class);

    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = addBookActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(addBookActivityTestRule.getActivity().getResources().getString(R.string.add_book_text)));

    }

    @Test
    public void textViewsTest() {
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.enter_detail_button)).check(matches(withText(R.string.enter_detail_manually_button_text)));

        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                onChildView(withId(R.id.book_thumbnail_image)).perform(click(), closeSoftKeyboard());

        onView(withId(R.id.search_result_count)).check(matches(withText(containsString(addBookActivityTestRule.getActivity().getResources().getString(R.string.results_string)))));
        onView(withId(R.id.cancel)).check(matches(withText(R.string.cancel)));
        onView(withId(R.id.next)).check(matches(withText(R.string.next)));

    }

    @Test
    public void layoutViewsWithManuallyFillButtonTest() {
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.manual_search_button_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.search_result_to_add_book)).check(matches(not(isDisplayed())));
        onView(withId(R.id.select_book_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));

        onView(withId(R.id.enter_detail_button)).perform(click());
        onView(withId(R.id.back)).perform(click());

        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.manual_search_button_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.search_result_to_add_book)).check(matches(not(isDisplayed())));
        onView(withId(R.id.select_book_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));
        ActionBar actionBar = addBookActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(addBookActivityTestRule.getActivity().getResources().getString(R.string.add_book_text)));


    }

    @Test
    public void layoutViewsWithEditTextTypeTest() {
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.manual_search_button_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.search_result_to_add_book)).check(matches(not(isDisplayed())));
        onView(withId(R.id.select_book_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));

        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());


        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.manual_search_button_layout)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_result_to_add_book)).check(matches(isDisplayed()));
        onView(withId(R.id.select_book_text)).check(matches(isDisplayed()));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));


    }

    @Test
    public void layoutViewsOnBottomButtonClicksTest() {
        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());

        onView(withId(R.id.select_book_text)).check(matches(isDisplayed()));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));

        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                onChildView(withId(R.id.book_thumbnail_image)).perform(click(), closeSoftKeyboard());


        onView(withId(R.id.next_buttons)).check(matches(isDisplayed()));
        onView(withId(R.id.select_book_text)).check(matches(not(isDisplayed())));

        onView(withId(R.id.next)).perform(click());
        onView(withId(R.id.back)).perform(click());

        onView(withId(R.id.search_box)).check(matches(withText("Shakti")));
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.search_option)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step1)));
        onView(withId(R.id.search_box)).check(matches(withHint(R.string.search_hint)));
        onView(withId(R.id.manual_search_button_layout)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_result_to_add_book)).check(matches(isDisplayed()));
        onView(withId(R.id.select_book_text)).check(matches(isDisplayed()));
        onView(withId(R.id.next_buttons)).check(matches(not(isDisplayed())));
        ActionBar actionBar = addBookActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(addBookActivityTestRule.getActivity().getResources().getString(R.string.add_book_text)));


    }

    @Test
    public void gridViewTest() {

        onView(withId(R.id.search_result_to_add_book)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());
        onView(withId(R.id.search_result_to_add_book)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_name))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_author))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).
                check(matches(hasDescendant(withId(R.id.book_lender))));

        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_name))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_author))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(1).
                check(matches(hasDescendant(withId(R.id.book_lender))));

        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(1).perform(closeSoftKeyboard());

        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(6).perform(click());

        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(6).
                check(matches(hasDescendant(withId(R.id.book_name))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(6).
                check(matches(hasDescendant(withId(R.id.book_author))));
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(6).
                check(matches(hasDescendant(withId(R.id.book_lender))));


    }


}
