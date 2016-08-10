package com.talentica.presentation.leadCapturePage.addmybook.view;

import android.content.Context;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.Collection;

public interface AddBookMainView extends LoadDataView {
    void showSearchBookResults(Collection<BookModel> books);

    void selectBook(BookModel bookModel);

    void setActionBarItems();

    void setDiscriptionBarText();

    void enableButtonForManualDetail();

    void disableButtonForManualDetail();

    void enableSearchBokResults();

    void disableSearchBookResults();

    void updateSearchBookResultQuantity();

    void moveToManuallyFillDetailPage();

    void moveToAutoFillDetailPage();

    void enableBottomTextBar();

    void disableBottomTextBar();

    void enableBottomNextButtons();

    void disableBottomNextButtons();

    void removeBookOpacity();

    Context context();
}
