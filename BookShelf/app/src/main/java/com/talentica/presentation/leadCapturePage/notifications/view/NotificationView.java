package com.talentica.presentation.leadCapturePage.notifications.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;
import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;

import java.util.Collection;

public interface NotificationView extends LoadDataView {
    void renderNotificationView(Collection<NotificationModel> notificationModels);

    void refreshNotifications();

    void setActionBar();
}
