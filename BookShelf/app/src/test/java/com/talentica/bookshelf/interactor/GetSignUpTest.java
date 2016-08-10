
package com.talentica.bookshelf.interactor;


import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISignUpRepository;
import com.talentica.domain.usecases.GetSignUpStatus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetSignUpTest {

    private GetSignUpStatus getSignUpStatus;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ISignUpRepository iSignUpRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getSignUpStatus = new GetSignUpStatus(iSignUpRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        getSignUpStatus.buildUseCaseObservable();

        verify(iSignUpRepository).tryForSignUp(anyString(), anyString(), anyString());
        verifyNoMoreInteractions(iSignUpRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
