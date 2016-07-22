package com.talentica.presentation.login.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.login.view.SignInView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class SignInPresenter implements Presenter {
    private final String Tag = "SignInPresenter";
    private final BaseUseCase getSignInStatus;
    private SignInView signInView;

    @Inject
    public SignInPresenter(@Named("signInStatusUseCase") BaseUseCase getSignInStatus) {
        Log.e(Tag, "SignInPresenter" + "signInStatusUseCase");
        this.getSignInStatus = getSignInStatus;

    }

    public void setView(@NonNull SignInView view) {
        signInView = view;
    }

    public void initialize() {
        hideViewRetry();

    }

    private void hideViewRetry() {
        signInView.hideRetry();
    }

    private void showViewRetry() {
        signInView.showRetry();
    }


    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(signInView.context(),
                errorBundle.getException());
        signInView.showError(errorMessage);
    }

    public void getSignInStatus(String username, String password) {
        Log.e(Tag, "getSignInStatus" + "username " + username + " password " + password);

        if (username.length() == 0 || password.length() == 0) {
            signInView.showError("");
        } else {
            getSignInStatus.execute(username, password, new SignInStatusSubscriber());
        }

    }



    private void actAsPerResult(String status) {
        if (status == "false") {
            signInView.showError("");
        }
        //not implemented fullly
        else {
            signInView.moveToLeadCapturePage();

        }

    }

    private void hideViewLoading() {
        signInView.hideLoading();
    }

    public void signUpTextClicked() {
        signInView.moveToSignUp();
    }

    public void forgetPassTextClicked() {
        signInView.moveToForgotPassword();
    }

    public void signInButtonClicked(String username, String password) {
        signInView.disableError();
        getSignInStatus(username, password);
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getSignInStatus.unsubscribe();
        this.signInView = null;
    }

    private final class SignInStatusSubscriber extends DefaultSubscriber<String> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(Tag, " " + "onError");
            hideViewLoading();
            //  showErrorMessage(new DefaultErrorBundle(new Exception(e)));
            showViewRetry();
        }

        @Override
        public void onNext(String status) {
            Log.e(Tag, "SignInPresenter" + " " + status);
            actAsPerResult(status);
        }
    }

}
