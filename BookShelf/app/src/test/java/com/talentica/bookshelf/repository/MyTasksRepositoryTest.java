package com.talentica.bookshelf.repository;

import com.talentica.data.entity.BooksRequestedToUserEntity;
import com.talentica.data.entity.mapper.book.EntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.MyTaskRepository;
import com.talentica.domain.model.BooksRequestedToUser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class MyTasksRepositoryTest {
    private MyTaskRepository repository;

    @Mock
    private DummyRestApi dummyRestApi;
    @Mock
    private EntityDataMapper bookEntityDataMapper;

    @Mock
    private BooksRequestedToUserEntity bookEntityRequested;
    @Mock
    private BooksRequestedToUser bookRequested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new MyTaskRepository(
                bookEntityDataMapper, dummyRestApi);

    }

    @Test
    public void testForBooksRequested() {
        List<BooksRequestedToUserEntity> bookList = new ArrayList<>();
        bookList.add(new BooksRequestedToUserEntity());
        given(dummyRestApi.bookRequestedToUser()).willReturn(Observable.just(bookList));
        repository.booksRequestedToUser();
        verify(dummyRestApi).bookRequestedToUser();

    }

    @Test
    public void testForBookRequestAccepted() {

        given(dummyRestApi.bookRequestedAccepted(2, "shakti")).willReturn(Observable.just(true));
        repository.bookRequestAccepted(2, "shakti");
        verify(dummyRestApi).bookRequestedAccepted(anyInt(), anyString());

    }

    @Test
    public void testForBookRequestedRejected() {
        given(dummyRestApi.bookRequestedRejected(2, "shakti")).willReturn(Observable.just(true));
        repository.bookRequestRejected(2, "shakti");
        verify(dummyRestApi).bookRequestedRejected(anyInt(), anyString());

    }
}
