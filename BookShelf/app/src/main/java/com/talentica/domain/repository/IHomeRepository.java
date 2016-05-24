package com.talentica.domain.repository;


import com.talentica.domain.model.Book;

import java.util.List;

import rx.Observable;

public interface IHomeRepository {


    Observable<List<Book>> askForRecentlyAddedBooks();

    Observable<List<Book>> askForMostReadBooks();

    Observable<List<Book>> searchBooks();
}
