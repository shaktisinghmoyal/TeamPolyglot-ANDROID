package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ILeadCapturePageRepository;
import com.talentica.domain.usecases.SaveRecentSearches;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SaveRecentSearchesTest {
    private SaveRecentSearches saveRecentSearches;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ILeadCapturePageRepository iLeadCapturePageRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        saveRecentSearches = new SaveRecentSearches(iLeadCapturePageRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testSaveRecentSearchObservableHappyCase() {
        saveRecentSearches.buildUseCaseObservable();

        verify(iLeadCapturePageRepository).saveRecentSearch(null);
        verifyNoMoreInteractions(iLeadCapturePageRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
