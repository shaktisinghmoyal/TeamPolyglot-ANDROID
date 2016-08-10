package com.talentica.domain.repository;

import rx.Observable;

public interface ILeadCapturePageRepository {
    void saveRecentSearch(String recentSearch);

    Observable<Boolean> requestToBorrowBook(int bookId);
}
