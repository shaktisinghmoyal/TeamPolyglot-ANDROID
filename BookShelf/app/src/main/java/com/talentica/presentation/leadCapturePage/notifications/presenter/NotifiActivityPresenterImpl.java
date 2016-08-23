package com.talentica.presentation.leadCapturePage.notifications.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.notifications.view.NotificationActivityView;

import javax.inject.Inject;

public class NotifiActivityPresenterImpl implements NotifiActivityPresenter {
    private final String Tag = "NotifiActivityPresenter";
    private NotificationActivityView notificationActivityView;

    @Inject
    public NotifiActivityPresenterImpl() {
        Log.e(Tag, "TaskActivityPresenter");
    }

    public void setView(@NonNull NotificationActivityView view) {
        notificationActivityView = view;
    }

    public void initialize() {
        notificationActivityView.setFirstFragment();

    }

    @Override
    public void setActionBar(int id) {
        notificationActivityView.setActionBar(id);
    }
}
