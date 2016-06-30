package com.talentica.presentation.login.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;

public interface ForgetPassView extends LoadDataView {
    void sendLinkToResetPass();

    void moveToSignIn();

}
