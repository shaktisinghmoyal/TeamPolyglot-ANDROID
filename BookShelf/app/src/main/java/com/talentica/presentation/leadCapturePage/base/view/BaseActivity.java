package com.talentica.presentation.leadCapturePage.base.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.talentica.presentation.BookShelfApplication;
import com.talentica.presentation.internal.di.components.ApplicationComponent;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.navigation.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends Activity {

    @Inject
    Navigator navigator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }


    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BookShelfApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
