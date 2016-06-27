package com.talentica.presentation.login.view.activity;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.talentica.R;
import com.talentica.databinding.LoginActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.AuthenticationComponent;
import com.talentica.presentation.internal.di.components.DaggerAuthenticationComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseActivity;
import com.talentica.presentation.login.view.fragment.SignInFragment;

public class LoginActivity extends BaseActivity implements HasComponent<AuthenticationComponent> {
    public static Activity activity;
    private AuthenticationComponent authenticationComponent;
    private LoginActivityBinding loginActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.login_activity);
        setSupportActionBar(loginActivityBinding.loginToolbar);
        loginActivityBinding.loginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick backButton", "");
                setFragment(actionBarTypeEnum.SIGNIN);


            }
        });
        initializeInjector();
        activity = authenticationComponent.activity();
        if (savedInstanceState == null) {
            setFragment(actionBarTypeEnum.SIGNIN);

        }


    }

    ;

    private void setFragment(actionBarTypeEnum enumType) {
        navigator.addFragment(this, R.id.login_page_fragment_container, new SignInFragment(), "login_fragment");
        setActionViewBar(enumType);
    }

    private void initializeInjector() {
        authenticationComponent = DaggerAuthenticationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    public void setActionViewBar(actionBarTypeEnum actionBarType) {
        Log.e("setActionViewBar", "actionBarType");
        switch (actionBarType) {
            case SIGNIN:
                getSupportActionBar().hide();
                break;

            case SIGNUP:
                Log.e("setActionViewBar", "Singh");
                getSupportActionBar().show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Sign Up");

                break;

            case FORGOTPASS:
                getSupportActionBar().show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Forgot Password");

                break;


        }



    }

    @Override
    public AuthenticationComponent getComponent() {
        return authenticationComponent;
    }


    public static enum actionBarTypeEnum {SIGNIN, SIGNUP, FORGOTPASS}
}
