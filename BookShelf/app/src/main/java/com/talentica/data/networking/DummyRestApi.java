package com.talentica.data.networking;

import android.util.Log;

import com.talentica.data.entity.BookEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DummyRestApi extends BaseClassForMethods {
    private final String Tag = "DummyRestApi";
    @Inject
    public DummyRestApi() {

    }

    public Observable<List<BookEntity>> recentlyAddedBookList() {
//        Log.e("DummyRestApi", "recentlyAddedBookList");
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
        Log.e(Tag, "recentlyAddedBookList");
        return Observable.just(makeBookList());
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
    }

    public Observable<List<BookEntity>> predictiveSearch() {
        Log.e(Tag, "predictiveSearch");
        return Observable.just(makeBookList());
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
    }


    public Observable<JSONObject> dummyTopSearches() {
        Log.e(Tag, "recentlyAddedBookList");
        return Observable.just(new JSONObject());
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
    }

    public Observable<JSONObject> dummyLoginModule(String username, String password) {
//        Log.e("DummyRestApi", "dummyLoginModule");
        JSONObject object = new JSONObject();

        try {
            if (username.equals("reshakt") & password.equals("SHAmoy123")) {

                object.put("result", "true");
            } else {
                object.put("result", "false");
            }

        } catch (JSONException e) {

        }
        return Observable.just(object);
//        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>(){
//
    }


    public List<BookEntity> makeBookList() {
        List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
        BookEntity bookEntity;
        for (int i = 0; i < 6; i++) {
            bookEntity = new BookEntity();
            bookEntity.setBookName("BookName" + i);
            bookEntity.setLenderName("LenderName" + i);
            bookEntity.setAuthersName("AutherName" + i);
            bookEntityList.add(bookEntity);
        }

        return bookEntityList;
    }

}
