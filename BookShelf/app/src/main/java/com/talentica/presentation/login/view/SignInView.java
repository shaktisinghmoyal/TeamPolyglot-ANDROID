package com.talentica.presentation.login.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;

public interface SignInView extends LoadDataView {
    void signIn();

    void moveToSignUp();

    void moveToForgotPassword();

    void moveToLeadCapturePage();

}
