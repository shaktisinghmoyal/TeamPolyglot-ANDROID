
package com.talentica.bookshelf.interactor;


import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISignInRepository;
import com.talentica.domain.usecases.GetSignInStatus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetSignInStatusTest {

    private static final int FAKE_USER_ID = 123;

    private GetSignInStatus getSignInStatus;

    @Mock
    private ISignInRepository iSignInRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getSignInStatus = new GetSignInStatus(iSignInRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetUserDetailsUseCaseObservableHappyCase() {
        getSignInStatus.buildUseCaseObservable();

        verify(iSignInRepository).tryForSignIn(anyString(), anyString());
        verifyNoMoreInteractions(iSignInRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}
