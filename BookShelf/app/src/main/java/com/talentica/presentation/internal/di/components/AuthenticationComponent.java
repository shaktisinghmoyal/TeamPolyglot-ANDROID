package com.talentica.presentation.internal.di.components;

import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.AuthenticationModule;
import com.talentica.presentation.login.view.fragment.ForgetPassFragment;
import com.talentica.presentation.login.view.fragment.SignInFragment;
import com.talentica.presentation.login.view.fragment.SignUpFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AuthenticationModule.class})
public interface AuthenticationComponent extends ActivityComponent {
    void inject(SignInFragment fragment);

    void inject(SignUpFragment fragment);

    void inject(ForgetPassFragment fragment);


}
