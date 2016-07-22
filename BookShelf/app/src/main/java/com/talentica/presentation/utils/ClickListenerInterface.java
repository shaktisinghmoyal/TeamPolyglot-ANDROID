package com.talentica.presentation.utils;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface ClickListenerInterface {
    void onSuggestionItemClicked(final String suggestionString);

    void onGridViewBookClicked(final BookModel bookModel);

    void onRecyclerViewBookClicked(final BookModel bookModel);
}
