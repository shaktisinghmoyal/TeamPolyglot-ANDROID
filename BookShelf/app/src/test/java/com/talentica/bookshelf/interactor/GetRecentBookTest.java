package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;
import com.talentica.domain.usecases.GetRecentlyAddedBookList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetRecentBookTest {


    private GetRecentlyAddedBookList getRecentlyAddedBookList;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IHomeRepository iHomeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getRecentlyAddedBookList = new GetRecentlyAddedBookList(iHomeRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        getRecentlyAddedBookList.buildUseCaseObservable();

        verify(iHomeRepository).askForRecentlyAddedBooks();
        verifyNoMoreInteractions(iHomeRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
