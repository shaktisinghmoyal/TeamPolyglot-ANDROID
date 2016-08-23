package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.INotificationRepository;
import com.talentica.domain.usecases.GetNotifications;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetNotificationsTest {
    @Mock
    INotificationRepository repository;
    private GetNotifications getNotifications;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getNotifications = new GetNotifications(repository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetNotificationsUseCaseObservable() {
        getNotifications.buildUseCaseObservable();

        verify(repository).getNotifications();
        verifyNoMoreInteractions(repository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
