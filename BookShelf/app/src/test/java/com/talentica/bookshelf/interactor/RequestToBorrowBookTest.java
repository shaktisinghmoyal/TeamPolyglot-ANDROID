package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ILeadCapturePageRepository;
import com.talentica.domain.usecases.RequestToBorrowBook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class RequestToBorrowBookTest {
    private RequestToBorrowBook requestToBorrowBook;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ILeadCapturePageRepository iLeadCapturePageRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        requestToBorrowBook = new RequestToBorrowBook(iLeadCapturePageRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testRequestToBorrowUseCaseObservableHappyCase() {
        requestToBorrowBook.buildUseCaseObservable();

        verify(iLeadCapturePageRepository).requestToBorrowBook(0);
        verifyNoMoreInteractions(iLeadCapturePageRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
