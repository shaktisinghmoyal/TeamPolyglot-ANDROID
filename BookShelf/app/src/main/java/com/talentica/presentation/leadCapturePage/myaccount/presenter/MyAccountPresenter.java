package com.talentica.presentation.leadCapturePage.myaccount.presenter;

import android.support.annotation.NonNull;

import com.talentica.presentation.leadCapturePage.myaccount.view.MyAccountView;

import javax.inject.Inject;

public class MyAccountPresenter implements IMyAccountPresenter {
    private MyAccountView view;

    @Inject
    public MyAccountPresenter() {
    }


    public void setView(@NonNull MyAccountView view) {
        this.view = view;

    }

    public void initialize() {
        setActionBar();
    }


    private void setActionBar() {
        view.setActionBarTitle();
    }
}
