package com.talentica.presentation.leadCapturePage.addmybook.presenter;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface IAddBookMainPresenter {
    void showManualAddButton();

    void updateSearchCount();

    void onGridViewBookClick(BookModel bookModel);

    void loadMoreBooks();

    void searchBooksForQueryString(String query);

    void showSelectBookBottomText();

    void showNextBottomButtons();

    void onNextButtonClicked();

    void onCancelButtonClicked();

    void retry();

    void onManuallyAddDetailsButtonClicked();
}
