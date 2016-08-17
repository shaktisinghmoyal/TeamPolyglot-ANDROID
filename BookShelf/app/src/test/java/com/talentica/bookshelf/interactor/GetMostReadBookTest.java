package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;
import com.talentica.domain.usecases.GetMostReadBookList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetMostReadBookTest {


    private GetMostReadBookList getMostReadBookTest;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IHomeRepository iHomeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getMostReadBookTest = new GetMostReadBookList(iHomeRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetMostReadBookUseCaseObservableHappyCase() {
        getMostReadBookTest.buildUseCaseObservable();

        verify(iHomeRepository).askForMostReadBooks();
        verifyNoMoreInteractions(iHomeRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

}
