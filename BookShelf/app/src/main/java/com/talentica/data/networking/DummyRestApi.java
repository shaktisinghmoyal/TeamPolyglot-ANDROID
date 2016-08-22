package com.talentica.data.networking;

import android.util.Log;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.BooksRequestedToUserEntity;
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

    public Observable<List<BooksRequestedToUserEntity>> bookRequestedToUser() {
        Log.e(Tag, "bookRequestedToUser");
        return Observable.create(new Observable.OnSubscribe<List<BooksRequestedToUserEntity>>() {

            @Override
            public void call(Subscriber<? super List<BooksRequestedToUserEntity>> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        Log.e("DummyRestApi", "makeBookList");
                        subscriber.onNext(makeRequestedBookList());
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

    public Observable<Boolean> bookRequestedAccepted(int bookId, String requesteeName) {
        Log.e(Tag, "bookRequestedAccepted");
        return Observable.just(true);

    }

    public Observable<Boolean> bookRequestedRejected(int bookId, String requesteeName) {
        Log.e(Tag, "bookRequestedRejected");
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

    public List<BooksRequestedToUserEntity> makeRequestedBookList() {
        List<BooksRequestedToUserEntity> requestedBookList = new ArrayList<BooksRequestedToUserEntity>();

        BooksRequestedToUserEntity book1 = new BooksRequestedToUserEntity();
        book1.setBookName("The Lal Killa");
        book1.setBookAuther("Shakti  Moyal");
        book1.setRequestedBy("Akash trivedi");
        book1.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book2 = new BooksRequestedToUserEntity();
        book2.setBookName("The Lal Killa");
        book2.setBookAuther(" Singh Moyal");
        book2.setRequestedBy("Akash trivedi");
        book2.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book3 = new BooksRequestedToUserEntity();
        book3.setBookName("The Lal Killa");
        book3.setBookAuther("Shakti Singh Moyal");
        book3.setRequestedBy("Akash trivedi");
        book3.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book4 = new BooksRequestedToUserEntity();
        book4.setBookName("The Lal Killa");
        book4.setBookAuther("Shakti Singh ");
        book4.setRequestedBy("Akash trivedi");
        book4.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book5 = new BooksRequestedToUserEntity();
        book5.setBookName("The Lal Killa");
        book5.setBookAuther("Bhakti Singh Moyal");
        book5.setRequestedBy("Akash trivedi");
        book5.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book6 = new BooksRequestedToUserEntity();
        book6.setBookName("The Lal Killa");
        book6.setBookAuther("Bhakti Singh ");
        book6.setRequestedBy("Akash trivedi");
        book6.setRequestedDate("09 mar");

        BooksRequestedToUserEntity book7 = new BooksRequestedToUserEntity();
        book7.setBookName("The Lal Killa");
        book7.setBookAuther("Bhakti  Moyal");
        book7.setRequestedBy("Akash trivedi");
        book7.setRequestedDate("09 mar");
        requestedBookList.add(book1);
        requestedBookList.add(book2);
        requestedBookList.add(book3);
        requestedBookList.add(book4);
        requestedBookList.add(book5);
        requestedBookList.add(book6);
        requestedBookList.add(book7);

        return requestedBookList;
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
