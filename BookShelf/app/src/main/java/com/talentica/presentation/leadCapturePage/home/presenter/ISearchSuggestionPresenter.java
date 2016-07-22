package com.talentica.presentation.leadCapturePage.home.presenter;

public interface ISearchSuggestionPresenter {
    void loadRecentSearchSuggestions();

    void loadTopSearchSuggestions();

    void onItemClicked(String suggestionString);
}
