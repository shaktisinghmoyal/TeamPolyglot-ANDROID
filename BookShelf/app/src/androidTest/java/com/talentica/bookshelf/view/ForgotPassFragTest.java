package com.talentica.bookshelf.view;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.talentica.R;
import com.talentica.presentation.login.view.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class ForgotPassFragTest {


    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void moveToSignUpFragment() {
        onView(withId(R.id.forget_password)).perform(click());
    }


    @Test
    public void validCredential() {

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.email_for_forget_pass)).perform(typeText("shakti.singh0708@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.send_mail_button)).perform(click());

        Fragment fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("SignInFragment"));
    }

    @Test
    public void invalidCredential() {
        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.email_for_forget_pass)).perform(typeText("invalidemail"), closeSoftKeyboard());
        onView(withId(R.id.send_mail_button)).perform(click());
        onView(withId(R.id.error_text)).check(matches(isDisplayed()));
    }

    @Test
    public void withoutCredential() {

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.send_mail_button)).perform(click());
        onView(withId(R.id.error_text)).check(matches(isDisplayed()));

    }
}
