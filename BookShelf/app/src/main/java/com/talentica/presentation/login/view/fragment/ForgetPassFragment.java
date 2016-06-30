package com.talentica.presentation.login.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.ForgetPasswordBinding;
import com.talentica.presentation.internal.di.components.AuthenticationComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.login.presenter.ForgetPassPresenter;
import com.talentica.presentation.login.view.ForgetPassView;
import com.talentica.presentation.login.view.activity.LoginActivity;

import javax.inject.Inject;

public class ForgetPassFragment extends BaseFragment implements ForgetPassView, View.OnClickListener {


//    @Inject
//    public Navigator navigator;

    @Inject
    public ForgetPassPresenter forgetPassPresenter;

    private ForgetPasswordBinding forgetPassBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AuthenticationComponent.class).inject(this);
    }

    @Override
    public void moveToSignIn() {
        ((LoginActivity) getActivity()).navigator.addFragment((LoginActivity) getActivity(), R.id.login_page_fragment_container, new SignInFragment(), "sign_in_fragment");
        ((LoginActivity) getActivity()).setActionViewBar(LoginActivity.actionBarTypeEnum.SIGNIN);// inject karna chahiye

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        forgetPassBinding = DataBindingUtil.inflate(inflater, R.layout.forget_password, container, false);
        return forgetPassBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgetPassPresenter.setView(this);
        if (savedInstanceState == null) {
            initializeForgetPassViews();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        forgetPassBinding.sendMailButton.setOnClickListener(this);

    }

    private void initializeForgetPassViews() {
        forgetPassPresenter.initialize();
    }


    @Override
    public void sendLinkToResetPass() {
        forgetPassBinding.errorText.setVisibility(View.INVISIBLE);
        forgetPassPresenter.getForgetPassStatus(forgetPassBinding.emailForForgetPass.getText().toString());

    }


    @Override
    public void showLoading() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        getActivity().setProgressBarIndeterminateVisibility(false);
    }


    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Log.e("showError", "message  " + message);
        forgetPassBinding.errorText.setVisibility(View.VISIBLE);


    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onClick(View v) {
        sendLinkToResetPass();
    }

    @Override
    public void onResume() {
        super.onResume();
        forgetPassPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        forgetPassPresenter.pause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        forgetPassPresenter = null;
    }
}
