package com.talentica.presentation.internal.di.components;


import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.NotificationModule;
import com.talentica.presentation.leadCapturePage.notifications.view.activity.NotificationActivity;
import com.talentica.presentation.leadCapturePage.notifications.view.fragment.NotificationFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NotificationModule.class})
public interface NotificationsComponent extends ActivityComponent {

    void inject(NotificationActivity notificationActivity);

    void inject(NotificationFragment notificationFragment);
}
