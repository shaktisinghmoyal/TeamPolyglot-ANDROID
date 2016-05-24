package com.talentica.presentation.leadCapturePage.home.presenter;

public interface IHomePagePresenter {

    void loadRecentlyAddedBooks();

    void loadMostReadBooks();

    void loadNextRecentlyAddedBooksOnSwipe();

    void loadNextMostReadBooksOnSwipe();

    void onSearch(String string);

    void onBookClick();
}
