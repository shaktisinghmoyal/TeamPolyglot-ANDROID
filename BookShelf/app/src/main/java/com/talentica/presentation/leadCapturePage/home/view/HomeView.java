package com.talentica.presentation.leadCapturePage.home.view;


import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.Collection;

public interface HomeView extends LoadDataView {

    void displayRecentlyAddedBooks(Collection<BookModel> books);

    void displayMostReadBooks(Collection<BookModel> books);

    void viewBook(BookModel bookModel);


}
