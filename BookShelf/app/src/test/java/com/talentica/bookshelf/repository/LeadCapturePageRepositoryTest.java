package com.talentica.bookshelf.repository;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.LeadCapturePageRepository;
import com.talentica.data.storage.SharedPreferencesStorage;
import com.talentica.domain.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class LeadCapturePageRepositoryTest {
    private LeadCapturePageRepository leadCapturePageRepository;

    @Mock
    private SharedPreferencesStorage sharedPreferencesStorage;
    @Mock
    private DummyRestApi dummyRestApi;
    @Mock
    private BookEntity bookEntity;
    @Mock
    private Book book;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leadCapturePageRepository = new LeadCapturePageRepository(
                sharedPreferencesStorage, dummyRestApi);

    }

    @Test
    public void testForSaveRecentSearch() {

        // given(sharedPreferencesStorage.saveRecentSearchStrings(anyString()));
        leadCapturePageRepository.saveRecentSearch("test");
        verify(sharedPreferencesStorage).saveRecentSearchStrings(anyString());

    }

    @Test
    public void testForRequestToBorrowBook() {

        given(dummyRestApi.borrowRequest()).willReturn(Observable.just(true));
        leadCapturePageRepository.requestToBorrowBook(1);
        verify(dummyRestApi).borrowRequest();

    }
}
