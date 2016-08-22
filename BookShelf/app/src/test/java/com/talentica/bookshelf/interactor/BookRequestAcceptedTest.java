package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IMyTaskRepository;
import com.talentica.domain.usecases.BookRequestAccepted;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class BookRequestAcceptedTest {
    @Mock
    IMyTaskRepository repository;
    private BookRequestAccepted bookRequestAccepted;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookRequestAccepted = new BookRequestAccepted(repository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testSubmitMyBookUseCaseObservableHappyCase() {
        bookRequestAccepted.buildUseCaseObservable();

        verify(repository).bookRequestAccepted(0, null);
        verifyNoMoreInteractions(repository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
