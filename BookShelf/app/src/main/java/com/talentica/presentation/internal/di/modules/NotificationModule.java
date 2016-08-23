package com.talentica.presentation.internal.di.modules;

import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.GetNotifications;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationModule {

    public NotificationModule() {
    }

    @Provides
    @PerActivity
    @Named("getNotificationUseCase")
    BaseUseCase provideGetNotificationsBaseUseCase(GetNotifications getNotifications) {
        return getNotifications;
    }

}
