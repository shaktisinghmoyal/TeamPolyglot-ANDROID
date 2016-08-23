package com.talentica.bookshelf.view;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.tasks.view.activity.MyTaskActivity;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

//import static org.hamcrest.CoreMatchers.is;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class MyTaskFragmentTest {
    final AtomicReference<String> bookName = new AtomicReference<String>();
    final AtomicReference<String> writer = new AtomicReference<String>();
    @Rule
    public ActivityTestRule<MyTaskActivity> myTaskActivityTestRule = new ActivityTestRule<MyTaskActivity>(MyTaskActivity.class);

    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = myTaskActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(true));
        assertThat(actionBar.getTitle().toString(), containsString(myTaskActivityTestRule.getActivity().getResources().getString(R.string.my_tasks)));

    }


    @Test
    public void requestAcceptedTest() {


        onView(withId(R.id.tasks_list))
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
                        bookName.set(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString());
                        writer.set(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString());
                    }
                }));


        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.accept_requested_task)));


        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new RecyclerViewActions.PositionableRecyclerViewAction() {
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
                        Assert.assertThat(bookName.toString(), is(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString()));
                        Assert.assertThat(writer.toString(), is(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString()));

                    }
                }));


    }

    @Test
    public void requestRejectedTest() {


        onView(withId(R.id.tasks_list))
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
                        bookName.set(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString());
                        writer.set(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString());
                    }
                }));


        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cancel_requested_task)));


        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new RecyclerViewActions.PositionableRecyclerViewAction() {
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
                        Assert.assertThat(bookName.toString(), is(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString()));
                        Assert.assertThat(writer.toString(), is(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString()));

                    }
                }));


    }


    @Test
    public void scrollingTest() {

        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, new RecyclerViewActions.PositionableRecyclerViewAction() {
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
                        bookName.set(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString());
                        writer.set(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString());
                    }
                }));

        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cancel_requested_task)));

        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, new RecyclerViewActions.PositionableRecyclerViewAction() {
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
                        Assert.assertThat(bookName.toString(), is(((TextView) view.findViewById(R.id.notification_book_name)).getText().toString()));
                        Assert.assertThat(writer.toString(), is(((TextView) view.findViewById(R.id.requested_book_auther_name)).getText().toString()));

                    }
                }));

    }

    public ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) {
                    v.performClick();
                }
            }
        };


    }

}
