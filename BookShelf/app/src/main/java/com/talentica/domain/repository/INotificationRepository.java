package com.talentica.domain.repository;

import com.talentica.domain.model.Notification;

import java.util.List;

import rx.Observable;

public interface INotificationRepository {

    Observable<List<Notification>> getNotifications();
}
