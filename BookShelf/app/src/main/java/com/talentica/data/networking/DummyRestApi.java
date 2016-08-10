package com.talentica.data.networking;

import android.util.Log;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.exception.NetworkConnectionException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class DummyRestApi extends BaseClassForMethods {
    private final String Tag = "DummyRestApi";
    @Inject
    public DummyRestApi() {

    }

    public Observable<List<BookEntity>> recentlyAddedBookList() {
        Log.e(Tag, "recentlyAddedBookList");
        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>() {

            @Override
            public void call(Subscriber<? super List<BookEntity>> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        Log.e("DummyRestApi", "makeBookList");
                        subscriber.onNext(makeBookList());
                        subscriber.onCompleted();

                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    Log.e("DummyRestApi", "error");
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });

    }

    public Observable<List<BookEntity>> mostReadBookList() {
        Log.e(Tag, "recentlyAddedBookList");
        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>() {

            @Override
            public void call(Subscriber<? super List<BookEntity>> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        Log.e("DummyRestApi", "makeBookList");
                        subscriber.onNext(makeBookList());
                        subscriber.onCompleted();

                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    Log.e("DummyRestApi", "error");
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });

    }


    public Observable<List<BookEntity>> predictiveSearch() {
        Log.e(Tag, "predictiveSearch");
        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>() {

            @Override
            public void call(Subscriber<? super List<BookEntity>> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        Log.e("DummyRestApi", "makeBookList");
                        subscriber.onNext(makeBookList());
                        subscriber.onCompleted();

                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    Log.e("DummyRestApi", "error");
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });


    }

    public Observable<Boolean> borrowRequest() {
        Log.e(Tag, "borrowRequest");
        return Observable.just(true);

    }

    public Observable<Boolean> submitBook(BookEntity bookEntity) {
        Log.e(Tag, "submitBook");
        return Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        Log.e("DummyRestApi", "submitBook");
                        subscriber.onNext(true);
                        subscriber.onCompleted();

                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    Log.e("DummyRestApi", "error");
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });

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

            bookEntity = new BookEntity();
        bookEntity.setBookName("A Tale of Two Cities");
        bookEntity.setLenderName("Rohit Gandhi");
        bookEntity.setAuthersName("Charles Dicken");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");

            bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookName("David Copperfield");
        bookEntity.setLenderName("Mahatma Gandhi");
        bookEntity.setAuthersName("Charles Dicken");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");
        bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookName("Great Expectation");
        bookEntity.setLenderName("Anna gandhi");
        bookEntity.setAuthersName("Charles gandhi");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");
        bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookName("Karma Bhoomi");
        bookEntity.setLenderName("Shakti Singh");
        bookEntity.setAuthersName("Prem Chand");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");
        bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookName("Godan ");
        bookEntity.setLenderName("Virendra Singh");
        bookEntity.setAuthersName("Prem Chand");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");
        bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookName("Nirmala ");
        bookEntity.setLenderName("Nirmal Kumar");
        bookEntity.setAuthersName("Nirmal Jeet");
        bookEntity.setBinding("Paperback");
        bookEntity.setPublishDate("Feb 2016");
        bookEntity.setPublisher("Moyal Sahab publication");
        bookEntity.setIsbn13Numbers("9781530144679");
        bookEntity.setIsbn10Numbers("1503044671");
        bookEntity.setEdition("1");
        bookEntity.setBookPrice("1000");
        bookEntityList.add(bookEntity);


        return bookEntityList;
    }


}
