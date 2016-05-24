package com.talentica.data.networking;

import com.talentica.data.entity.BookEntity;

import java.util.List;

import rx.Observable;

public class RestApiImpl extends BaseClassForMethods implements RestApi, BaseURL {
    @Override
    public Observable<List<BookEntity>> recentlyAddedBookList() {
        return null;
    }

    @Override
    public Observable<List<BookEntity>> mostReadBookList() {
        return null;
    }

    @Override
    public Observable<List<BookEntity>> abstractSearch(String stringForSearch) {
        return null;
    }


}
