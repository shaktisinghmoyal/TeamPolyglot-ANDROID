package com.talentica.presentation.login.view.activity;


import android.os.Bundle;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.base.view.BaseActivity;
import com.talentica.presentation.login.view.fragment.SignInFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        if (savedInstanceState == null) {
            navigator.addFragment(this, R.id.login_page_fragment_container, new SignInFragment(), "login_fragment");
        }


    }

    public void setActionBar() {


    }

}
