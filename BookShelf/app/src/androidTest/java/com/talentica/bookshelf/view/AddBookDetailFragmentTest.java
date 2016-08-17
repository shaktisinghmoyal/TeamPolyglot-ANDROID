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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class AddBookDetailFragmentTest {

    @Rule
    public ActivityTestRule<AddMyBookActivity> addBookActivityTestRule = new ActivityTestRule<AddMyBookActivity>(AddMyBookActivity.class);

    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = addBookActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), is(addBookActivityTestRule.getActivity().getResources().getString(R.string.add_book_text)));

    }

    @Test
    public void autoFillPortionOfBookDetailTest() {
        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.next)).perform(click());
        onView(withId(R.id.auto_add_upper_portion)).check(matches(isDisplayed()));
        onView(withId(R.id.manually_add_upper_portion)).check(matches(not(isDisplayed())));
    }

    @Test
    public void manualFillUpperPortionOfBookDetailTest() {
        onView(withId(R.id.enter_detail_button)).perform(click());
        onView(withId(R.id.auto_add_upper_portion)).check(matches(not(isDisplayed())));
        onView(withId(R.id.manually_add_upper_portion)).check(matches(isDisplayed()));
    }

    @Test
    public void discriptionLayerTest() {
        onView(withId(R.id.enter_detail_button)).perform(click());
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.add_manually)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step2)));

        onView(withId(R.id.back)).perform(click());

        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton());
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.next)).perform(click());
        onView(withId(R.id.page_discription)).check(matches(withText(R.string.add_detail)));
        onView(withId(R.id.step_text)).check(matches(withText(R.string.step2)));
    }

    @Test
    public void autoFillPageBottomPartTest() {
        onView(withId(R.id.search_box)).perform(typeText("Shakti"), pressImeActionButton(), closeSoftKeyboard());
        onData(anything()).inAdapterView(withId(R.id.books_result_to_add)).atPosition(0).perform(click());
        onView(withId(R.id.next)).perform(click());

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.first_radio_button)).perform(click());

        onView(withId(R.id.first_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.second_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.third_radio_button)).check(matches(not(isChecked())));


        onView(withId(R.id.second_radio_button)).perform(click());

        onView(withId(R.id.second_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.first_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.third_radio_button)).check(matches(not(isChecked())));

        onView(withId(R.id.third_radio_button)).perform(click());

        onView(withId(R.id.third_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.first_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.second_radio_button)).check(matches(not(isChecked())));

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.tags_edit_text)).perform(typeText("Horror"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());


        onView(withId(R.id.comment_edit_text)).perform(scrollTo());
        onView(withId(R.id.genre_spinner)).perform(click());
        onData(is("Mystery")).perform(click());
        onView(withId(R.id.genre_spinner)).check(matches(withSpinnerText(containsString("Mystery"))));

        onView(withId(R.id.submit)).perform(click());


    }

    @Test
    public void manualFillFulPageTest() {
        onView(withId(R.id.enter_detail_button)).perform(click());

        onView(withId(R.id.book_name_edit_text)).perform(typeText("Mohan Jodaro"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.author_name_edit_text)).perform(scrollTo());
        onView(withId(R.id.author_name_edit_text)).perform(typeText("Shakti Singh"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.isbn10)).perform(scrollTo());
        onView(withId(R.id.isbn10)).perform(typeText("1530144671"), closeSoftKeyboard());
        onView(withId(R.id.isbn13)).perform(typeText("9781530144679"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.book_price_edit_text)).perform(scrollTo());
        onView(withId(R.id.publisher_name_edit_text)).perform(typeText("Mohil Publication"), closeSoftKeyboard());
        onView(withId(R.id.binding_spinner)).perform(click());
        onData(is("Card Bound")).perform(click());
        onView(withId(R.id.binding_spinner)).check(matches(withSpinnerText(containsString("Card Bound"))));
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.book_price_edit_text)).perform(scrollTo());
        onView(withId(R.id.book_price_edit_text)).perform(typeText("1101"), closeSoftKeyboard());


        onView(withId(R.id.tags_edit_text)).perform(scrollTo());
        onView(withId(R.id.first_radio_button)).perform(click());
        onView(withId(R.id.first_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.second_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.third_radio_button)).check(matches(not(isChecked())));


        onView(withId(R.id.second_radio_button)).perform(click());

        onView(withId(R.id.second_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.first_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.third_radio_button)).check(matches(not(isChecked())));

        onView(withId(R.id.third_radio_button)).perform(click());

        onView(withId(R.id.third_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.first_radio_button)).check(matches(not(isChecked())));
        onView(withId(R.id.second_radio_button)).check(matches(not(isChecked())));

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.comment_edit_text)).perform(scrollTo());
        onView(withId(R.id.tags_edit_text)).perform(typeText("Horror"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());


        onView(withId(R.id.comment_edit_text)).perform(scrollTo());
        onView(withId(R.id.genre_spinner)).perform(click());
        onData(is("Mystery")).perform(click());
        onView(withId(R.id.genre_spinner)).check(matches(withSpinnerText(containsString("Mystery"))));

        onView(withId(R.id.submit)).perform(click());


    }
}
