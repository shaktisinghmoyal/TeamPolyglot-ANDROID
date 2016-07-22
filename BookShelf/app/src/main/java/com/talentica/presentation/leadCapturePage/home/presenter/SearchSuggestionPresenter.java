package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.view.SearchSuggestionsView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class SearchSuggestionPresenter implements ISearchSuggestionPresenter, Presenter {
    private final String Tag = "SearchSuggestionPresenter";
    private final BaseUseCase getTopSearchSuggestion;
    private final BaseUseCase getRecentSearchSuggestion;
    private final int TOP_SEARCH_QUERY = 1;
    private final int RECENT_SEARCH_QUERY = 2;
    private SearchSuggestionsView searchSuggestionsView;


    @Inject
    public SearchSuggestionPresenter(@Named("getTopSearchesUseCase") BaseUseCase getTopSearchSuggestion, @Named("getRecentSearchesUseCase") BaseUseCase getRecentSearchSuggestion) {
        this.getTopSearchSuggestion = getTopSearchSuggestion;
        this.getRecentSearchSuggestion = getRecentSearchSuggestion;
    }

    public void setView(@NonNull SearchSuggestionsView view) {
        searchSuggestionsView = view;
    }


    public void initialize() {
        setActionBar();
        showViewLoading();
        loadRecentSearchSuggestions();
        loadTopSearchSuggestions();
    }

    private void setActionBar() {
        searchSuggestionsView.setActionSearchBar();
    }

    private void showViewLoading() {
        searchSuggestionsView.showLoading();
    }


    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(searchSuggestionsView.context(),
                errorBundle.getException());
        this.searchSuggestionsView.showError(errorMessage);
    }

    @Override
    public void loadRecentSearchSuggestions() {
        getRecentSearchSuggestion.execute(new SearchSuggestionSubscriber(RECENT_SEARCH_QUERY));


    }

    @Override
    public void loadTopSearchSuggestions() {

        getTopSearchSuggestion.execute(new SearchSuggestionSubscriber(TOP_SEARCH_QUERY));
    }

    @Override
    public void onItemClicked(String suggestionString) {
        searchSuggestionsView.showSuggestedBooks(suggestionString);
    }

    public void showRecentSuggestions(String[] recentSuggestions) {
        searchSuggestionsView.showRecentSearches(recentSuggestions);
    }

    public void showTopSuggestions(String[] topSuggestions) {
        searchSuggestionsView.showTopSearches(topSuggestions);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    private void showViewRetry() {
        searchSuggestionsView.showRetry();
    }

    private void hideViewLoading() {
        searchSuggestionsView.hideLoading();
    }


    private final class SearchSuggestionSubscriber extends DefaultSubscriber<String[]> {

        int queryType;

        SearchSuggestionSubscriber(int queryType) {
            this.queryType = queryType;
        }

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }

        @Override
        public void onNext(String[] suggestions) {
            if (queryType == TOP_SEARCH_QUERY) {
                showTopSuggestions(suggestions);
            } else {
                showRecentSuggestions(suggestions);
            }
        }
    }
}
