package com.talentica.domain.repository;

import rx.Observable;

public interface ISearchSuggestionRepository {
    Observable<String[]> getTopSearches();

    Observable<String[]> getRecentSearches();
}
