package com.talentica.presentation.leadCapturePage.tasks.presenter;

import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;

public interface IMyTasksPresenter extends Presenter {
    void onClickOk(BooksRequestedToUserModel bookRequested, int position);

    void onCLickCancel(BooksRequestedToUserModel bookRequested, int position);

    void retry();

    void loadBookRequestedToUser();
}
