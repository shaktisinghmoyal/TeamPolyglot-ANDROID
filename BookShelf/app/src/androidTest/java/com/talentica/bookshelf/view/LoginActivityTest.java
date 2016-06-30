package com.talentica.bookshelf.view;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.talentica.R;
import com.talentica.presentation.login.view.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void appBarTitleTest() {

        ActionBar actionBar = loginActivityTestRule.getActivity().getSupportActionBar();
        assertThat(actionBar.isShowing(), is(false));


        onView(withId(R.id.signup_text)).perform(click());
        assertThat(actionBar.isShowing(), is(true));
        onView(allOf(withText("Sign Up"), not(isClickable()))).check(matches(isDisplayed()));
        onView(withContentDescription("Navigate up")).perform(click());
        assertThat(actionBar.isShowing(), is(false));


        onView(withId(R.id.forget_password)).perform(click());
        assertThat(actionBar.isShowing(), is(true));
        onView(withText("Forgot Password")).check(matches(isDisplayed()));
        onView(withContentDescription("Navigate up")).perform(click());
        assertThat(actionBar.isShowing(), is(false));
    }


    @Test
    public void containsFragmentTest() {

        Fragment fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("SignInFragment"));

        onView(withId(R.id.signup_text)).perform(click());
        fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("SignUpFragment"));
        onView(withContentDescription("Navigate up")).perform(click());

        fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("SignInFragment"));

        onView(withId(R.id.forget_password)).perform(click());
        fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("ForgetPassFragment"));
        onView(withContentDescription("Navigate up")).perform(click());

        fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));

        assertThat(fragment.getClass().getSimpleName(), is("SignInFragment"));
    }


}
