package com.talentica.data.repository;

import com.talentica.data.storage.DataBase;
import com.talentica.domain.model.Notification;
import com.talentica.domain.repository.INotificationRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class NotificationRepository implements INotificationRepository {

    private final String Tag = "NotificationRepository";
    private DataBase dataBase;


    @Inject
    public NotificationRepository(DataBase dataBase) {
        this.dataBase = dataBase;

    }

    @Override
    public Observable<List<Notification>> getNotifications() {

        return Observable.just(dataBase.makeRequestedBookList());
    }


}
