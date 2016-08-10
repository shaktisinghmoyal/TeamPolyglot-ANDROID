package com.talentica.presentation.leadCapturePage.home.presenter;

public interface IBookActivityPresenter {
    void renderBook();

    void onBorrowButtonClick();

    void onYesOptionCLicked(int bookId);

    void onNoOptionClicked();

}
