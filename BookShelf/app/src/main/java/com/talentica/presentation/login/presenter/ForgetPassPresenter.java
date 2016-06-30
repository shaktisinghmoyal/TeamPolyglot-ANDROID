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
import com.talentica.presentation.login.view.ForgetPassView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ForgetPassPresenter implements Presenter {

    private final BaseUseCase forgetPassStatus;
    private ForgetPassView forgetPassView;

    @Inject
    public ForgetPassPresenter(@Named("forgetPassStatsUseCase") BaseUseCase forgetPassStatus) {
        this.forgetPassStatus = forgetPassStatus;

    }

    public void setView(@NonNull ForgetPassView view) {
        forgetPassView = view;
    }

    public void initialize() {
        hideViewRetry();

    }

    private void hideViewRetry() {
        forgetPassView.hideRetry();
    }

    private void showViewRetry() {
        forgetPassView.showRetry();
    }


    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(forgetPassView.context(),
                errorBundle.getException());
        forgetPassView.showError(errorMessage);
    }

    public void getForgetPassStatus(String email) {
        Log.e("getSignInStatus", "email " + email + "  ");

        if (email.length() == 0) {
            forgetPassView.showError("");
        } else {
            forgetPassStatus.execute(email, new SignInStatusSubscriber());
        }
    }

    private void actAsPerResult(String status) {
        //not implemented fullly
        if (status == "false") {
            forgetPassView.showError("");
        }
        //not implemented fullly
        else {
            forgetPassView.moveToSignIn();
        }

    }

    private void hideViewLoading() {
        forgetPassView.hideLoading();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        forgetPassStatus.unsubscribe();
        this.forgetPassView = null;
    }

    private final class SignInStatusSubscriber extends DefaultSubscriber<String> {


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
