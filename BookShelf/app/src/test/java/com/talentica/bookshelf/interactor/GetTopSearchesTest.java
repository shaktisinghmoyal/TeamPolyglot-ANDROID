package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISearchSuggestionRepository;
import com.talentica.domain.usecases.GetTopSearches;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetTopSearchesTest {
    private GetTopSearches getTopSearches;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ISearchSuggestionRepository iSearchSuggestionRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getTopSearches = new GetTopSearches(iSearchSuggestionRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetTopSearchesUseCaseObservableHappyCase() {
        getTopSearches.buildUseCaseObservable();

        verify(iSearchSuggestionRepository).getTopSearches();
        verifyNoMoreInteractions(iSearchSuggestionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
