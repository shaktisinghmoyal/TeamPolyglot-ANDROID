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
public class SignUpFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void moveToSignUpFragment() {
        onView(withId(R.id.signup_text)).perform(click());
    }


    @Test
    public void validSignUpCredential() {

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));

        onView(withId(R.id.sign_up_full_name_edit_text_id)).perform(typeText("Shakti Singh Moyal"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_email_edit_text_id)).perform(typeText("reshakt"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_password_edit_text_id)).perform(typeText("SHAmoy123"), closeSoftKeyboard());

        onView(withId(R.id.sign_up_button)).perform(click());

        Fragment fragment = loginActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.login_page_fragment_container);
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment.getClass().getSimpleName(), is("SignInFragment"));
    }

    @Test
    public void invalidSignUpCredential() {
        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));

        onView(withId(R.id.sign_up_full_name_edit_text_id)).perform(typeText("invaliduser"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_email_edit_text_id)).perform(typeText("invalidemail"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_password_edit_text_id)).perform(typeText("invalidpassword"), closeSoftKeyboard());

        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.error_text)).check(matches(isDisplayed()));
    }

    @Test
    public void withoutSignUpCredential() {

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.error_text)).check(matches(isDisplayed()));

    }

}