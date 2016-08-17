package com.talentica.bookshelf.repository;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.SearchSuggestionRepository;
import com.talentica.data.storage.SharedPreferencesStorage;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SearchSuggestionRepositoryTest {

    @Mock
    private DummyRestApi dummyRestApi;

    @Mock
    private SharedPreferencesStorage sharedPreferencesStorage;

    @Mock
    private SearchSuggestionRepository searchSuggestionRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        searchSuggestionRepository = new SearchSuggestionRepository(dummyRestApi, sharedPreferencesStorage);

    }

    @Test
    public void testForTopSearches() {
        given(dummyRestApi.dummyTopSearches()).willReturn(Observable.just(new JSONObject()));
        searchSuggestionRepository.getTopSearches();
        verify(dummyRestApi).dummyTopSearches();

    }

    @Test
    public void testForRecentSearches() {

        // given(sharedPreferencesStorage.saveRecentSearchStrings(anyString()));
        searchSuggestionRepository.getRecentSearches();
        verify(sharedPreferencesStorage).getRecentSearchStrings();

    }
}
