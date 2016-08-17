package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;
import com.talentica.domain.usecases.GetBooksGridResults;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetBooksGridResultTest {
    private GetBooksGridResults getBooksGridResults;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IHomeRepository iHomeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getBooksGridResults = new GetBooksGridResults(iHomeRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetBooksGridUseCaseObservableHappyCase() {
        getBooksGridResults.buildUseCaseObservable();

        verify(iHomeRepository).searchBooks();
        verifyNoMoreInteractions(iHomeRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
