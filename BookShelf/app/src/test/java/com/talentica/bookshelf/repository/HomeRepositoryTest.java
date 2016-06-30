package com.talentica.bookshelf.repository;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.book.BookEntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.HomeRepository;
import com.talentica.domain.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class HomeRepositoryTest {


    private HomeRepository homeRepository;

    @Mock
    private BookEntityDataMapper bookEntityDataMapper;
    @Mock
    private DummyRestApi dummyRestApi;
    @Mock
    private BookEntity bookEntity;
    @Mock
    private Book book;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homeRepository = new HomeRepository(
                bookEntityDataMapper, dummyRestApi);

    }

    @Test
    public void testForRecentlyAddedBooks() {
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity());
        given(dummyRestApi.recentlyAddedBookList()).willReturn(Observable.just(bookList));

        homeRepository.askForRecentlyAddedBooks();

        verify(dummyRestApi).recentlyAddedBookList();
//        verify(bookEntityDataMapper).transform(bookList);
    }

    @Test
    public void testForMostAddedBooks() {
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity());
        given(dummyRestApi.mostReadBookList()).willReturn(Observable.just(bookList));

        homeRepository.askForMostReadBooks();

        verify(dummyRestApi).mostReadBookList();
//        verify(bookEntityDataMapper).transform(bookList);
    }
}
