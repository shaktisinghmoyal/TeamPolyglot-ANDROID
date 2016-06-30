package com.talentica.bookshelf.presenter;

import android.content.Context;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.R;
import com.talentica.domain.usecases.GetSignInStatus;
import com.talentica.presentation.login.presenter.SignInPresenter;
import com.talentica.presentation.login.view.SignInView;
import com.talentica.presentation.login.view.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SignInPresenterTest {

    @Rule
    public ActivityTestRule<LoginActivity> mainActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private SignInPresenter signInPresenter;
    private Context mockContext;
    private SignInView signInView;
    private GetSignInStatus getSignInStatus;

    @Before
    public void setView() {
        // MockitoAnnotations.initMocks(this);
        signInView = Mockito.mock(SignInView.class);
        getSignInStatus = Mockito.mock(GetSignInStatus.class);
        mockContext = Mockito.mock(Context.class);
        //  System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
        signInPresenter = new SignInPresenter(getSignInStatus);
        signInPresenter.setView(signInView);


    }

    @Test
    public void setSignInPresenterTest() {


        given(signInView.context()).willReturn(mockContext);

        signInPresenter.initialize();

        verify(signInView).hideRetry();

        onView(withId(R.id.login_email)).perform(typeText("invaliduser"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("invalidpassword"), closeSoftKeyboard());

        signInPresenter.getSignInStatus("invaliduser", "invalidpassword");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // verify(getSignInStatus).buildUseCaseObservable();
        verify(getSignInStatus).execute(anyString(), anyString(), any(Subscriber.class));
//        verify(getSignInStatus).execute();

    }
}
