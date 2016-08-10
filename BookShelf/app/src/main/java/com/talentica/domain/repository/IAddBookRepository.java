package com.talentica.domain.repository;

import com.talentica.domain.model.Book;

import java.util.List;

import rx.Observable;

public interface IAddBookRepository {
    Observable<List<Book>> elasticSearchForBooks(String query);

    Observable<Boolean> submitBook(Book book);
}
