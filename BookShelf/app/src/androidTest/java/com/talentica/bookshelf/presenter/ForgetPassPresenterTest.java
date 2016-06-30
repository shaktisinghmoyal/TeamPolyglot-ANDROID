package com.talentica.bookshelf.presenter;


import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.R;
import com.talentica.domain.usecases.GetForgetPassStatus;
import com.talentica.presentation.login.presenter.ForgetPassPresenter;
import com.talentica.presentation.login.view.ForgetPassView;
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
public class ForgetPassPresenterTest {


    @Rule
    public ActivityTestRule<LoginActivity> mainActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private ForgetPassPresenter forgetPassPresenter;
    private Context mockContext;
    private ForgetPassView forgetPassView;
    private GetForgetPassStatus getForgetPassStatus;

    @Before
    public void setView() {
        // MockitoAnnotations.initMocks(this);
        onView(withId(R.id.forget_password)).perform(click());
        forgetPassView = Mockito.mock(ForgetPassView.class);
        getForgetPassStatus = Mockito.mock(GetForgetPassStatus.class);
        mockContext = Mockito.mock(Context.class);
        //  System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
        forgetPassPresenter = new ForgetPassPresenter(getForgetPassStatus);
        forgetPassPresenter.setView(forgetPassView);


    }

    @Test
    public void setForgetPassPresenterTest() {


        given(forgetPassView.context()).willReturn(mockContext);

        forgetPassPresenter.initialize();

        verify(forgetPassView).hideRetry();

        onView(withId(R.id.email_for_forget_pass)).perform(typeText("invalidemail"), closeSoftKeyboard());
        forgetPassPresenter.getForgetPassStatus("invalidemail");

        verify(getForgetPassStatus).execute(anyString(), any(Subscriber.class));

    }
}
