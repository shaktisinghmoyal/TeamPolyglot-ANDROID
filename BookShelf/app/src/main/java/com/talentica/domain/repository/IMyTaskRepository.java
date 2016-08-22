package com.talentica.domain.repository;

import com.talentica.domain.model.BooksRequestedToUser;

import java.util.List;

import rx.Observable;

public interface IMyTaskRepository {
    Observable<Boolean> bookRequestAccepted(int bookId, String userName);

    Observable<Boolean> bookRequestRejected(int bookId, String userName);

    Observable<List<BooksRequestedToUser>> booksRequestedToUser();
}
