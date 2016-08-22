package com.talentica.bookshelf.repository;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.book.EntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.AddBookRepository;
import com.talentica.domain.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class AddBookRepositoryTest {
    private AddBookRepository addBookRepository;

    @Mock
    private EntityDataMapper bookEntityDataMapper;
    @Mock
    private DummyRestApi dummyRestApi;
    @Mock
    private BookEntity bookEntity;
    @Mock
    private Book book;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        addBookRepository = new AddBookRepository(
                bookEntityDataMapper, dummyRestApi);

    }

    @Test
    public void testForElasticBookSearch() {
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity());
        given(dummyRestApi.predictiveSearch()).willReturn(Observable.just(bookList));
        addBookRepository.elasticSearchForBooks("test");
        verify(dummyRestApi).predictiveSearch();

    }

    @Test
    public void testForBookSubmition() {

        given(dummyRestApi.submitBook(any(BookEntity.class))).willReturn(Observable.just(true));
        addBookRepository.submitBook(new Book());
        verify(dummyRestApi).submitBook(any(BookEntity.class));

    }

}
