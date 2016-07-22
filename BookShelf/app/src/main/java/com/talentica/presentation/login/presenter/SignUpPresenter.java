package com.talentica.presentation.login.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.login.view.SignUpView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class SignUpPresenter implements Presenter {

    private final String Tag = "SignUpPresenter";
    private final BaseUseCase getSignUpStatus;
    private SignUpView signUpView;

    @Inject
    public SignUpPresenter(@Named("signUpStatusUseCase") BaseUseCase getSignUpStatus) {
        this.getSignUpStatus = getSignUpStatus;

    }

    public void setView(@NonNull SignUpView view) {
        signUpView = view;
    }

    public void initialize() {
        hideViewRetry();

    }

    private void hideViewRetry() {
        signUpView.hideRetry();
    }

    private void showViewRetry() {
        signUpView.showRetry();
    }


    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(signUpView.context(),
                errorBundle.getException());
        this.signUpView.showError(errorMessage);
    }

    public void getSignUpStatus(String username, String password, String fullName) {
        Log.e(Tag, "getSignInStatus" + "email/username " + username + " fullName " + fullName + " password " + password);


        if (username.length() == 0 || password.length() == 0 || fullName.length() == 0) {
            signUpView.showError("");
        } else {
            getSignUpStatus.execute(username, password, fullName, new SignUpStatusSubscriber());
        }
    }


    private void hideViewLoading() {
        signUpView.hideLoading();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getSignUpStatus.unsubscribe();
        this.signUpView = null;
    }

    private void actAsPerResult(String status) {
        //not implemented fullly
        if (status == "false") {
            signUpView.showError("");
        }
        //not implemented fullly
        else {
            signUpView.moveToSignIn();
        }

    }

    private final class SignUpStatusSubscriber extends DefaultSubscriber<String> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }

        @Override
        public void onNext(String status) {

            actAsPerResult(status);
        }
    }
}
