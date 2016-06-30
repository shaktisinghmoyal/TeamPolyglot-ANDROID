package com.talentica.bookshelf.presenter;


import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.R;
import com.talentica.domain.usecases.GetSignUpStatus;
import com.talentica.presentation.login.presenter.SignUpPresenter;
import com.talentica.presentation.login.view.SignUpView;
import com.talentica.presentation.login.view.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpPresenterTest {


    @Rule
    public ActivityTestRule<LoginActivity> mainActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private SignUpPresenter signUpPresenter;
    private Context mockContext;
    private SignUpView signUpView;
    private GetSignUpStatus getSignUpStatus;

    @Before
    public void setView() {
        // MockitoAnnotations.initMocks(this);
        onView(withId(R.id.signup_text)).perform(click());
        signUpView = Mockito.mock(SignUpView.class);
        getSignUpStatus = Mockito.mock(GetSignUpStatus.class);
        mockContext = Mockito.mock(Context.class);
        //  System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
        signUpPresenter = new SignUpPresenter(getSignUpStatus);
        signUpPresenter.setView(signUpView);


    }

    @Test
    public void setSignUpPresenterTest() {


        given(signUpView.context()).willReturn(mockContext);

        signUpPresenter.initialize();

        verify(signUpView).hideRetry();
        onView(withId(R.id.sign_up_full_name_edit_text_id)).perform(typeText("invalidFullName"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_email_edit_text_id)).perform(typeText("invalidemail"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_password_edit_text_id)).perform(typeText("invalidpassword"), closeSoftKeyboard());

        signUpPresenter.getSignUpStatus("invalidemail", "invalidpassword", "invalidFullName");
        verify(getSignUpStatus).execute(anyString(), anyString(), anyString(), any(Subscriber.class));

    }
}
