package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IMyTaskRepository;
import com.talentica.domain.usecases.BookRequestRejected;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class BookRequestRejectedTest {

    @Mock
    IMyTaskRepository repository;
    private BookRequestRejected bookRequestRejected;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookRequestRejected = new BookRequestRejected(repository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testSubmitMyBookUseCaseObservableHappyCase() {
        bookRequestRejected.buildUseCaseObservable();

        verify(repository).bookRequestRejected(0, null);
        verifyNoMoreInteractions(repository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
