
package com.talentica.presentation.internal.di.modules;

import android.content.Context;

import com.talentica.data.executor.JobExecutor;
import com.talentica.data.repository.HomeRepository;
import com.talentica.data.repository.ResetPassRepository;
import com.talentica.data.repository.SignInRepository;
import com.talentica.data.repository.SignUpRepository;
import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;
import com.talentica.domain.repository.IResetPassRepository;
import com.talentica.domain.repository.ISignInRepository;
import com.talentica.domain.repository.ISignUpRepository;
import com.talentica.presentation.BookShelfApplication;
import com.talentica.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final BookShelfApplication application;

    public ApplicationModule(BookShelfApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IHomeRepository provideIHomeRepository(HomeRepository homeRepository) {
        return homeRepository;
    }

    @Provides
    @Singleton
    ISignInRepository provideISignInRepository(SignInRepository signInRepository) {
        return signInRepository;
    }

    @Provides
    @Singleton
    ISignUpRepository provideISignUpRepository(SignUpRepository signUpRepository) {
        return signUpRepository;
    }


    @Provides
    @Singleton
    IResetPassRepository provideIResetPassRepository(ResetPassRepository resetPassRepository) {
        return resetPassRepository;
    }


}

