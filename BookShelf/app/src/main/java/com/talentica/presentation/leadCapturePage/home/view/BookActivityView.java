package com.talentica.presentation.leadCapturePage.home.view;

public interface BookActivityView {
    void showConfirmationDialog();

    void hideConfirmationDialog();

    void addBookDetailFragment();

    void displayWithOpacity();

    void hideOpacity();

    void showRequestResultMessage();

    void hideRequestButton();

    void unhideRequestButton();

    void enableRequestButton();

    void disableRequestButton();

}
