package com.talentica.bookshelf.view;


import android.support.test.espresso.UiController;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BookDetailFragmentTest {

    final AtomicReference<String> bookName = new AtomicReference<String>();
    final AtomicReference<String> writer = new AtomicReference<String>();
    @Rule
    public ActivityTestRule<MainActivity> bookDetailActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() {
        onView(withId(R.id.recycler_view_recently_added_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new RecyclerViewActions.PositionableRecyclerViewAction() {
                    @Override
                    public RecyclerViewActions.PositionableRecyclerViewAction atPosition(int position) {
                        return null;
                    }

                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "get text from text view";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        bookName.set(((TextView) view.findViewById(R.id.book_name)).getText().toString());
                        writer.set(((TextView) view.findViewById(R.id.book_author)).getText().toString());
                    }
                }));

        onView(withId(R.id.recycler_view_recently_added_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void testBookDetails() {

        onView(withId(R.id.book_name_in_book_layout)).check(matches(withText(bookName.get())));
        onView(withId(R.id.book_auther_in_book_layout)).check(matches(withText(writer.get())));
    }
}
