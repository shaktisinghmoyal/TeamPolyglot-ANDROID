package com.talentica.presentation.leadCapturePage.notifications.presenter;

import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;

public interface NotificationPresenter extends Presenter {

    void retry();


    void loadNotifications();
}
