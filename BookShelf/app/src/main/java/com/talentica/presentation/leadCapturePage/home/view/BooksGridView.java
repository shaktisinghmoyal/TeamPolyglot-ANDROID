package com.talentica.presentation.leadCapturePage.home.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.Collection;

public interface BooksGridView extends LoadDataView {
    void showSearchBookResults(Collection<BookModel> books);

    void viewBook(BookModel bookModel);

    void setFragmentTitle();
}
