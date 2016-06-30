package com.talentica.bookshelf.view;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;
import com.talentica.presentation.login.view.activity.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class SignInFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void validSignInCredential() {
        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasComponent(MainActivity.class.getName()));

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));

        onView(withId(R.id.login_email)).perform(typeText("reshakt"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("SHAmoy123"), closeSoftKeyboard());

        onView(withId(R.id.signin_button)).perform(click());
        intended(expectedIntent);
        Intents.release();
        //onView(withId(R.id.error_text)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidSignInCredential() {
        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));

        onView(withId(R.id.login_email)).perform(typeText("invaliduser"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("invalidpassword"), closeSoftKeyboard());

        onView(withId(R.id.signin_button)).perform(click());

        onView(withId(R.id.error_text)).check(matches(isDisplayed()));
    }

    @Test
    public void withoutSignInCredential() {

        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.signin_button)).perform(click());
        onView(withId(R.id.error_text)).check(matches(isDisplayed()));

    }

}
