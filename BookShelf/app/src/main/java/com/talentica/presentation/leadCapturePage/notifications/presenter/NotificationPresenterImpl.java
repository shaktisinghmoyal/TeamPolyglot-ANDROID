package com.talentica.presentation.leadCapturePage.notifications.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.model.Notification;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;
import com.talentica.presentation.leadCapturePage.notifications.view.NotificationView;
import com.talentica.presentation.mapper.DataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class NotificationPresenterImpl implements NotificationPresenter {
    private final String Tag = "NotifiPresenterImpl";
    private final BaseUseCase getNotification;
    private final DataMapper dataMapper;
    private NotificationView notificationView;


    @Inject
    public NotificationPresenterImpl(@Named("getNotificationUseCase") BaseUseCase getNotification, DataMapper dataMapper) {
        Log.e(Tag, "TaskActivityPresenter");
        this.getNotification = getNotification;
        this.dataMapper = dataMapper;


    }


    public void setView(@NonNull NotificationView view) {
        notificationView = view;
    }

    public void initialize() {
        hideViewRetry();
        showViewLoading();
        notificationView.setActionBar();
        loadNotifications();
    }

    private void showViewLoading() {
        notificationView.showLoading();
    }

    private void hideViewLoading() {

        notificationView.hideLoading();
    }

    private void showViewRetry() {
        notificationView.showRetry();
    }

    private void hideViewRetry() {
        notificationView.hideRetry();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getNotification.unsubscribe();
        notificationView = null;
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(notificationView.context(),
                errorBundle.getException());
        notificationView.showError(errorMessage);
    }

    @Override
    public void retry() {
        hideViewRetry();
        showViewLoading();
        loadNotifications();
    }

    private void showNotificationsResults(List<Notification> notifications) {

        final Collection<NotificationModel> bookRequestModelsCollection = this.dataMapper.transformNotifications(notifications);
        notificationView.renderNotificationView(bookRequestModelsCollection);


    }

    @Override
    public void loadNotifications() {
        getNotification.execute(new NotificationsSubscriber());
    }

    private final class NotificationsSubscriber extends DefaultSubscriber<List<Notification>> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));

        }

        @Override
        public void onNext(List<Notification> notifications) {
            hideViewLoading();
            showNotificationsResults(notifications);
        }
    }
}
