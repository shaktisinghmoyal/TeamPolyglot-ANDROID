package com.talentica.presentation.leadCapturePage.home.presenter;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface IBookPresenter {
    void loadBookWithDetail(BookModel bookModel);

    void enableOpacity();

    void disableOpacity();

    void renderSnackBar();
}
