package com.talentica.presentation.leadCapturePage.addmybook.presenter;

import android.content.Context;
import android.content.Intent;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public interface IAddBookDetailPresenter {
    void renderSnackBar(int messageId, Boolean isError);

    void renderDetailFillPage(int typeToFillPage);


    void onBookImageClicked();

    void addBookToServer(BookModel bookModel);

    void onGalleryChoiceResult(Context context, Intent data);

    void onCameraChoiceResult(Intent data);

    void onCancelButtonClicked();
}
