package com.talentica.presentation.leadCapturePage.home.presenter;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.Enums;

public interface IBooksGridViewPresenter {
    void loadBookSearchResults(Enums.gridViewType SearchType);

    void retry(Enums.gridViewType SearchType);

    void setFragmentPageTitle();

    void setActionBar();

    void onGridViewBookClick(BookModel bookModel);
}
