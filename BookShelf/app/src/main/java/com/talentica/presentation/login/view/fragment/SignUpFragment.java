package com.talentica.presentation.login.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.SignUpBinding;
import com.talentica.presentation.internal.di.components.AuthenticationComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.login.presenter.SignUpPresenter;
import com.talentica.presentation.login.view.SignUpView;
import com.talentica.presentation.login.view.activity.LoginActivity;

import javax.inject.Inject;

public class SignUpFragment extends BaseFragment implements SignUpView, View.OnClickListener {
//    @Inject
//    public Navigator navigator;

    @Inject
    public SignUpPresenter signUpPresenter;
    private SignUpBinding signUpBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AuthenticationComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpBinding = DataBindingUtil.inflate(inflater, R.layout.sign_up, container, false);
        return signUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpPresenter.setView(this);
        if (savedInstanceState == null) {
            initializeSignUpViews();
        }
    }

    private void initializeSignUpViews() {
        signUpPresenter.initialize();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpBinding.signUpButton.setOnClickListener(this);

    }

    @Override
    public void signUp() {
        signUpBinding.errorText.setVisibility(View.INVISIBLE);
        signUpPresenter.getSignUpStatus(signUpBinding.signUpEmailEditTextId.getText().toString(), signUpBinding.signUpPasswordEditTextId.getText().toString(), signUpBinding.signUpFullNameEditTextId.getText().toString());

    }

    @Override
    public void moveToSignIn() {
        ((LoginActivity) getActivity()).navigator.addFragment((LoginActivity) getActivity(), R.id.login_page_fragment_container, new SignInFragment(), "sign_in_fragment");
        ((LoginActivity) getActivity()).setActionViewBar(LoginActivity.actionBarTypeEnum.SIGNIN);// inject karna chahiye
    }

    @Override
    public void onClick(View v) {
        signUp();
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
        signUpBinding.errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        signUpPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        signUpPresenter.pause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signUpPresenter = null;
    }
}
