package com.talentica.bookshelf.interactor;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISearchSuggestionRepository;
import com.talentica.domain.usecases.GetRecentSearches;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetRecentSearchesTest {
    private GetRecentSearches getRecentSearches;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ISearchSuggestionRepository iSearchSuggestionRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getRecentSearches = new GetRecentSearches(iSearchSuggestionRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetRecentSearchesUseCaseObservableHappyCase() {
        getRecentSearches.buildUseCaseObservable();

        verify(iSearchSuggestionRepository).getRecentSearches();
        verifyNoMoreInteractions(iSearchSuggestionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
