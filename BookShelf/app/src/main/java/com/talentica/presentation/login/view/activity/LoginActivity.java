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
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.login.view.fragment.SignInFragment;
import com.talentica.presentation.utils.Enums;

public class LoginActivity extends BaseActivity implements HasComponent<AuthenticationComponent> {

    public static Activity activity;
    private final String Tag = "LoginActivity";
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
                Log.e(Tag, "onClick backButton" + "");
                setFragment(Enums.actionBarTypeEnum.SIGNIN);


            }
        });
        initializeInjector();
        activity = authenticationComponent.activity();
        if (savedInstanceState == null) {
            setFragment(Enums.actionBarTypeEnum.SIGNIN);

        }


    }

    ;

    private void setFragment(Enums.actionBarTypeEnum enumType) {
        navigator.addFragment(this, R.id.login_page_fragment_container, new SignInFragment(), "login_fragment");
        setActionViewBar(enumType);
    }

    private void initializeInjector() {
        authenticationComponent = DaggerAuthenticationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    public void setActionViewBar(Enums.actionBarTypeEnum actionBarType) {
        Log.e(Tag, "setActionViewBar" + "actionBarType");
        switch (actionBarType) {
            case SIGNIN:
                getSupportActionBar().hide();
                break;

            case SIGNUP:
                Log.e("setActionViewBar", "Singh");
                getSupportActionBar().show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getResources().getString(R.string.sign_up_title));

                break;

            case FORGOTPASS:
                getSupportActionBar().show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getResources().getString(R.string.forgot_pass_title));

                break;


        }



    }

    @Override
    public AuthenticationComponent getComponent() {
        return authenticationComponent;
    }


}
