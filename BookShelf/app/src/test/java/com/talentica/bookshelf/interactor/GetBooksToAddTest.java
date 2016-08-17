package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IAddBookRepository;
import com.talentica.domain.usecases.GetBooksToAdd;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetBooksToAddTest {
    private GetBooksToAdd getBooksToAdd;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IAddBookRepository iAddBookRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getBooksToAdd = new GetBooksToAdd(iAddBookRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetBooksToAddUseCaseObservableHappyCase() {
        getBooksToAdd.buildUseCaseObservable();

        verify(iAddBookRepository).elasticSearchForBooks(null);
        verifyNoMoreInteractions(iAddBookRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
