package com.talentica.presentation.login.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.domain.usecases.GetSignInStatus;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.login.view.SignInView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class SignInPresenter implements Presenter {
    private final BaseUseCase getSignInStatus;
    private SignInView signInView;

    @Inject
    public SignInPresenter(@Named("signInStatusUseCase") BaseUseCase getSignInStatus) {
        Log.e("SignInPresenter", "signInStatusUseCase");
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
        this.signInView.showError(errorMessage);
    }

    public void getSignInStatus(String username, String password) {
        ((GetSignInStatus) getSignInStatus).execute(username, password, new SignInStatusSubscriber());
    }


    private void actAsPerStatusOnView(String status) {
        //not implemented fullly
        signInView.moveToLeadCapturePage();

    }

    private void hideViewLoading() {
        signInView.hideLoading();
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
            Log.e("onError", "onError");
            hideViewLoading();
            //  showErrorMessage(new DefaultErrorBundle(new Exception(e)));
            showViewRetry();
        }

        @Override
        public void onNext(String status) {
            Log.e("SignInPresenter", "" + status);
            actAsPerStatusOnView(status);
        }
    }

}
