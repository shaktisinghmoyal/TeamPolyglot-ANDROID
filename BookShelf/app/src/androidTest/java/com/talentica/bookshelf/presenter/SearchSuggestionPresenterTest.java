package com.talentica.bookshelf.presenter;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.GetRecentSearches;
import com.talentica.domain.usecases.GetTopSearches;
import com.talentica.presentation.leadCapturePage.home.presenter.SearchSuggestionPresenter;
import com.talentica.presentation.leadCapturePage.home.view.SearchSuggestionsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SearchSuggestionPresenterTest {

    private SearchSuggestionPresenter presenter;
    private SearchSuggestionsView view;
    private GetTopSearches topSearchesUseCase;
    private GetRecentSearches recentSearchesUseCase;


    @Before
    public void setView() {

        view = Mockito.mock(SearchSuggestionsView.class);
        recentSearchesUseCase = Mockito.mock(GetRecentSearches.class);
        topSearchesUseCase = Mockito.mock(GetTopSearches.class);
        presenter = new SearchSuggestionPresenter(topSearchesUseCase, recentSearchesUseCase);
        presenter.setView(view);
    }


    @Test
    public void suggestionPresenterTest() {
        presenter.initialize();
        verify(view).setActionSearchBar();
        verify(view).showLoading();
        verify(recentSearchesUseCase).execute(any(Subscriber.class));
        verify(topSearchesUseCase).execute(any(Subscriber.class));

    }


}
