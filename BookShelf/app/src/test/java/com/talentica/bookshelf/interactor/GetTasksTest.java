package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IMyTaskRepository;
import com.talentica.domain.usecases.GetTasks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetTasksTest {

    @Mock
    IMyTaskRepository repository;
    private GetTasks getTasks;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getTasks = new GetTasks(repository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetRecentBookUseCaseObservableHappyCase() {
        getTasks.buildUseCaseObservable();

        verify(repository).booksRequestedToUser();
        verifyNoMoreInteractions(repository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
