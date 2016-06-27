package com.talentica.presentation.internal.di.modules;


import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.GetForgetPassStatus;
import com.talentica.domain.usecases.GetSignInStatus;
import com.talentica.domain.usecases.GetSignUpStatus;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationModule {
    public AuthenticationModule() {

    }

    @Provides
    @PerActivity
    @Named("signInStatusUseCase")
    BaseUseCase provideGetSignInStatusBaseUseCase(GetSignInStatus getSignInStatus) {
        return getSignInStatus;
    }

    @Provides
    @PerActivity
    @Named("signUpStatusUseCase")
    BaseUseCase provideGetSignUpStatusBaseUseCase(GetSignUpStatus getSignUpStatus) {
        return getSignUpStatus;
    }


    @Provides
    @PerActivity
    @Named("forgetPassStatsUseCase")
    BaseUseCase provideGetForgetPassStatusBaseUseCase(GetForgetPassStatus getForgetPassStatus) {
        return getForgetPassStatus;
    }


}
