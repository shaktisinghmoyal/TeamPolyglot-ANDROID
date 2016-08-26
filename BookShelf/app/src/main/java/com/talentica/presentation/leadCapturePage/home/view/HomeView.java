package com.talentica.presentation.leadCapturePage.home.view;


import android.content.Context;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.Collection;

public interface HomeView {

    void displayRecentlyAddedBooks(Collection<BookModel> books);

    void displayMostReadBooks(Collection<BookModel> books);

    void moveToBooksGridView(int fragmentTitleId);

    void viewBook(BookModel bookModel);

    void showLoadingRecycler1();

    void hideLoadingRecycler1();

    void showRetryRecycler1();

    void hideRetryRecycler1();

    void showErrorRecycler1(String message);

    void disableErrorRecycler1();

    void showLoadingRecycler2();

    void hideLoadingRecycler2();

    void showRetryRecycler2();

    void hideRetryRecycler2();

    void showErrorRecycler2(String message);

    void disableErrorRecycler2();

    Context context();

    void setActionSearchBar();

    void setBottomBarIconForHome();


}
