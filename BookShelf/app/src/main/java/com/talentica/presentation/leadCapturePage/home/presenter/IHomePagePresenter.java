package com.talentica.presentation.leadCapturePage.home.presenter;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface IHomePagePresenter {

    void loadRecentlyAddedBooks();

    void loadMostReadBooks();

    void loadNextRecentlyAddedBooksOnSwipe();

    void loadNextMostReadBooksOnSwipe();

    void onSearch(String string);

    void onBookClick(BookModel bookModel);

    void recentlyAddedViewAllClicked();

    void mostReadViewAllClicked();
}
