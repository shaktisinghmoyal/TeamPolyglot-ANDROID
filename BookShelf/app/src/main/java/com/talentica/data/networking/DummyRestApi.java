package com.talentica.data.networking;

import android.util.Log;

import com.talentica.data.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class DummyRestApi extends BaseClassForMethods {

    public Observable<List<BookEntity>> recentlyAddedBookList() {
        Log.e("DummyRestApi", "recentlyAddedBookList");
        return Observable.just(makeBookList());
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
//            @Override
//            public void call(Subscriber<? super List<BookEntity>> subscriber) {
//                if (isThereInternetConnection()) {
//                    try {
//                        Log.e("DummyRestApi", "makeBookList");
//                            subscriber.onNext(makeBookList());
//                            subscriber.onCompleted();
//
//                    } catch (Exception e) {
//                        subscriber.onError(new NetworkConnectionException(e.getCause()));
//                    }
//                } else {
//                    Log.e("DummyRestApi", "error");
//                    subscriber.onError(new NetworkConnectionException());
//                }
//            }
//        });
    }

    public Observable<List<BookEntity>> mostReadBookList() {
        Log.e("DummyRestApi", "recentlyAddedBookList");
        return Observable.just(makeBookList());
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
    }

    public List<BookEntity> makeBookList() {
        List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
        BookEntity bookEntity;
        for (int i = 0; i < 5; i++) {
            bookEntity = new BookEntity();
            bookEntity.setBookName("BookName" + i);
            bookEntity.setLenderName("LenderName" + i);
            bookEntity.setAuthersName("AutherName" + i);
            bookEntityList.add(bookEntity);
        }

        return bookEntityList;
    }

}
