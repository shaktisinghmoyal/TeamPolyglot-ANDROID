package com.talentica.presentation;

import android.app.Application;
import android.content.Context;

import com.talentica.presentation.internal.di.components.ApplicationComponent;
import com.talentica.presentation.internal.di.components.DaggerApplicationComponent;
import com.talentica.presentation.internal.di.modules.ApplicationModule;

public class BookShelfApplication extends Application {


    private static ApplicationComponent applicationComponent;

    public static Context getAppContext() {
        return applicationComponent.context();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
