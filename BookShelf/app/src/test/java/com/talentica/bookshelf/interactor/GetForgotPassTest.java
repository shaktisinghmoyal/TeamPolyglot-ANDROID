package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IResetPassRepository;
import com.talentica.domain.usecases.GetForgetPassStatus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetForgotPassTest {


    private GetForgetPassStatus getForgetPassStatus;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private IResetPassRepository iResetPassRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getForgetPassStatus = new GetForgetPassStatus(iResetPassRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        getForgetPassStatus.buildUseCaseObservable();

        verify(iResetPassRepository).tryForResetPass(anyString());
        verifyNoMoreInteractions(iResetPassRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
