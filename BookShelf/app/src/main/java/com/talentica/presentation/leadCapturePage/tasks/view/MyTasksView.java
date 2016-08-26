package com.talentica.presentation.leadCapturePage.tasks.view;

import com.talentica.presentation.leadCapturePage.base.view.LoadDataView;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;

import java.util.Collection;

public interface MyTasksView extends LoadDataView {
    void renderMyTasks(Collection<BooksRequestedToUserModel> books);

    void removeATask(int position);

    void displaySnackBar(Boolean isError);

    void refreshTask();

    void updateActionBar();

    void setActionBar();
}
