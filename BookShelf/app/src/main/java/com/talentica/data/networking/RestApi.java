package com.talentica.data.networking;

import com.talentica.data.entity.BookEntity;

import java.util.List;

import rx.Observable;

public interface RestApi extends BaseURL {
    //for book report operations
    String API_RECENTLY_ADDED_BOOK = API_BASE_URL + API_SEPERATOR + "recently-added";
    String API_MOST_READ_BOOK = API_BASE_URL + API_SEPERATOR + "most-read";
    //for book abstract search  operation
    String API_ABSTRACT_BOOK_SEARCH = API_BASE_URL + API_SEPERATOR + "search";


    Observable<List<BookEntity>> recentlyAddedBookList();

    Observable<List<BookEntity>> mostReadBookList();

    Observable<List<BookEntity>> abstractSearch(final String stringForSearch);



}
