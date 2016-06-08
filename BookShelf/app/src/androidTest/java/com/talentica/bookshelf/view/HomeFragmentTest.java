package com.talentica.bookshelf.view;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void otherTextTest() {
        onView(withId(R.id.recently_added_text)).check(matches(withText(R.string.recently_added_text)));
        onView(withId(R.id.recently_added_view_all_text)).check(matches(withText(R.string.view_all_text)));

        onView(withId(R.id.recently_added_books_arrow)).check(matches(isDisplayed()));


        onView(withId(R.id.most_read_text)).check(matches(withText(R.string.most_read_text)));
        onView(withId(R.id.most_read_view_all_text)).check(matches(withText(R.string.view_all_text)));

        onView(withId(R.id.most_read_books_arrow)).check(matches(isDisplayed()));
    }

    @Test
    public void recentlyAddedBooksRecyclerTest() {
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName0"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName0"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName0"))));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName1"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName1"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName1"))));


        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(not(hasDescendant(withText("BookName2")))));

        onView(withId(R.id.recycler_view_recently_added_list)).perform(RecyclerViewActions.scrollToPosition(3));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName2"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName2"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName2"))));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName3"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName3"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName3"))));

        onView(withId(R.id.recycler_view_recently_added_list)).perform(RecyclerViewActions.scrollToPosition(5));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName4"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName4"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName4"))));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName0"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName0"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName0"))));

        onView(withId(R.id.recycler_view_recently_added_list)).perform(RecyclerViewActions.scrollToPosition(7));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName1"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName1"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName1"))));

        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("BookName2"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("AutherName2"))));
        onView(withId(R.id.recycler_view_recently_added_list)).check(matches(hasDescendant(withText("LenderName2"))));

    }

    @Test
    public void mostReadBooksRecyclerTest() {
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName0"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName0"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName0"))));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName1"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName1"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName1"))));


        onView(withId(R.id.recycler_view_most_read_list)).check(matches(not(hasDescendant(withText("BookName2")))));

        onView(withId(R.id.recycler_view_most_read_list)).perform(RecyclerViewActions.scrollToPosition(3));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName2"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName2"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName2"))));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName3"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName3"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName3"))));

        onView(withId(R.id.recycler_view_most_read_list)).perform(RecyclerViewActions.scrollToPosition(5));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName4"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName4"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName4"))));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName0"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName0"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName0"))));

        onView(withId(R.id.recycler_view_most_read_list)).perform(RecyclerViewActions.scrollToPosition(7));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName1"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName1"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName1"))));

        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("BookName2"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("AutherName2"))));
        onView(withId(R.id.recycler_view_most_read_list)).check(matches(hasDescendant(withText("LenderName2"))));

    }


}
