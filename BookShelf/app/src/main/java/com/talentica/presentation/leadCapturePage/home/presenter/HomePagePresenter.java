package main.java.presentation.leadCapturePage.home.presenter;

public interface HomePagePresenter {

    void loadRecentlyAddedBooks();

    void loadMostReadBooks();

    void loadNextRecentlyAddedBooksOnSwipe();

    void loadNextMostReadBooksOnSwipe();

    void onSearch(String string);

    void onBookClick();
}
