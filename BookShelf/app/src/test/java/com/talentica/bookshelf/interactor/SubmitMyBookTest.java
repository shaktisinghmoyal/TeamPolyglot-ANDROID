package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IAddBookRepository;
import com.talentica.domain.usecases.SubmitMyBook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SubmitMyBookTest {
    private SubmitMyBook submitMyBook;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IAddBookRepository iAddBookRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        submitMyBook = new SubmitMyBook(iAddBookRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testSubmitMyBookUseCaseObservableHappyCase() {
        submitMyBook.buildUseCaseObservable();

        verify(iAddBookRepository).submitBook(null);
        verifyNoMoreInteractions(iAddBookRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
