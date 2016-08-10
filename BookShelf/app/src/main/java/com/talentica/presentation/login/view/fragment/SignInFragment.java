package com.talentica.presentation.login.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.SignInBinding;
import com.talentica.presentation.internal.di.components.AuthenticationComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.login.presenter.SignInPresenter;
import com.talentica.presentation.login.view.SignInView;
import com.talentica.presentation.login.view.activity.LoginActivity;
import com.talentica.presentation.utils.Enums;

import javax.inject.Inject;


public class SignInFragment extends BaseFragment implements SignInView, View.OnClickListener {

//    @Inject
//    public Navigator navigator;
private final String Tag = "SignInFragment";
    @Inject
    public SignInPresenter signInPresenter;

    private SignInBinding signInBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AuthenticationComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signInBinding = DataBindingUtil.inflate(inflater, R.layout.sign_in, container, false);
        return signInBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInPresenter.setView(this);
        if (savedInstanceState == null) {
            initializeSignInViews();
        }
    }

    private void initializeSignInViews() {
        signInPresenter.initialize();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signInBinding.signupText.setOnClickListener(this);
        signInBinding.forgetPassword.setOnClickListener(this);
        signInBinding.signinButton.setOnClickListener(this);

    }

    @Override
    public void moveToLeadCapturePage() {
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void signIn() {
        signInPresenter.signInButtonClicked(signInBinding.loginEmail.getText().toString(), signInBinding.loginPassword.getText().toString());

    }


    @Override
    public void moveToSignUp() {
        ((LoginActivity) getActivity()).navigator.addFragment((LoginActivity) getActivity(), R.id.login_page_fragment_container, new SignUpFragment(), "sign_up_fragment");
        ((LoginActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.SIGNUP);// inject karna chahiye
    }


    @Override
    public void moveToForgotPassword() {
        ((LoginActivity) getActivity()).navigator.addFragment((LoginActivity) getActivity(), R.id.login_page_fragment_container, new ForgetPassFragment(), "forget_pass_fragment");
        ((LoginActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.FORGOTPASS);// inject karna chahiye
    }



    @Override
    public void onClick(final View v) {
        switch (v.getId()) {


            case R.id.signin_button:
                signIn();
                break;

            case R.id.signup_text:
                signInPresenter.signUpTextClicked();
                break;

            case R.id.forget_password:
                signInPresenter.forgetPassTextClicked();
                break;


        }
    }

    @Override
    public void showLoading() {
        //getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        //getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Log.e(Tag, "showError" + " message  " + message);
        signInBinding.errorText.setVisibility(View.VISIBLE);


    }

    @Override
    public void disableError() {
        Log.e(Tag, "disableError" + "  ");
        signInBinding.errorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public Context context() {
        return null;
    }


    @Override
    public void onResume() {
        super.onResume();
        signInPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        signInPresenter.pause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signInPresenter = null;
    }
}
