package com.talentica.presentation.internal.di.modules;


import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.BookRequestAccepted;
import com.talentica.domain.usecases.BookRequestRejected;
import com.talentica.domain.usecases.GetTasks;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MyTaskModule {
    public MyTaskModule() {
    }

    @Provides
    @PerActivity
    @Named("bookRequestedToUserUseCase")
    BaseUseCase provideGetTasksBaseUseCase(GetTasks tasks) {
        return tasks;
    }

    @Provides
    @PerActivity
    @Named("bookRequestAcceptedUseCase")
    BaseUseCase provideBookRequestAcceptedBaseUseCase(BookRequestAccepted bookRequestAccepted) {
        return bookRequestAccepted;
    }

    @Provides
    @PerActivity
    @Named("bookRequestRejectedUseCase")
    BaseUseCase provideBookRequestRejectedBaseUseCase(BookRequestRejected bookRequestRejected) {
        return bookRequestRejected;
    }
}
