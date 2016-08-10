package com.talentica.presentation.leadCapturePage.home.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;

public interface SearchSuggestionsView extends LoadDataView {

    void showRecentSearches(String[] recentSearches);

    void showTopSearches(String[] topSearches);

    void showSuggestedBooks(String suggestedString);

    void setActionSearchBar();
}
