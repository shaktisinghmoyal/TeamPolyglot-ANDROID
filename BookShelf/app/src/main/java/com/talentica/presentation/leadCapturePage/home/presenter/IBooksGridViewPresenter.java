package com.talentica.presentation.leadCapturePage.home.presenter;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface IBooksGridViewPresenter {
    void loadBookSearchResults();

    void showFragmentPageTitle();

    void onGridViewBookClick(BookModel bookModel);
}
