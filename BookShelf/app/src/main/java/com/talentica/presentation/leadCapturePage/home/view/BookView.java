package com.talentica.presentation.leadCapturePage.home.view;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface BookView {

    void showBookWithDetail(BookModel bookModel);

    void setActionSearchBar();

    void setCustomViewOpaque();

    void removeCustomViewOpacity();

    void displaySnackBar();
}
