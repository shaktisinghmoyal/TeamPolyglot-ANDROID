package com.talentica.presentation.leadCapturePage.notifications.view.activity;

import android.os.Bundle;

import com.talentica.R;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.DaggerNotificationsComponent;
import com.talentica.presentation.internal.di.components.NotificationsComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.leadCapturePage.notifications.presenter.NotifiActivityPresenterImpl;
import com.talentica.presentation.leadCapturePage.notifications.view.NotificationActivityView;
import com.talentica.presentation.leadCapturePage.notifications.view.fragment.NotificationFragment;

import javax.inject.Inject;

public class NotificationActivity extends BaseActivity implements NotificationActivityView, HasComponent<NotificationsComponent> {

    private final String Tag = "NotificationActivity";
    @Inject
    public NotifiActivityPresenterImpl presenter;
    private NotificationsComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        initializeInjector();
        component.inject(this);
        presenter.setView(this);
        presenter.initialize();

    }


    private void initializeInjector() {
        component = DaggerNotificationsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }


    @Override
    public NotificationsComponent getComponent() {
        return component;
    }

    @Override
    public void setFirstFragment() {
        navigator.addFragment(this, R.id.notification_fragment_container, new NotificationFragment());
    }

    @Override
    public void setActionBar(int id) {
        getSupportActionBar().setTitle(getResources().getString(id));
    }
}
