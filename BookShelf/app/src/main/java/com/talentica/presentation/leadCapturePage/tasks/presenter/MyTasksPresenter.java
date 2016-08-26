package com.talentica.presentation.leadCapturePage.tasks.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.model.BooksRequestedToUser;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;
import com.talentica.presentation.leadCapturePage.tasks.view.MyTasksView;
import com.talentica.presentation.mapper.DataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class MyTasksPresenter implements IMyTasksPresenter {
    private final String Tag = "MyTasksPresenterImpl";
    private final BaseUseCase getRequestedBooks;
    private final BaseUseCase requestAccepted;
    private final BaseUseCase requestRejected;
    private final DataMapper dataMapper;
    private MyTasksView myTasksView;

    @Inject
    public MyTasksPresenter(@Named("bookRequestedToUserUseCase") BaseUseCase getRequestedBooks, @Named("bookRequestAcceptedUseCase") BaseUseCase requestAccepted, @Named("bookRequestRejectedUseCase") BaseUseCase requestRejected, DataMapper dataMapper) {
        Log.e(Tag, "TaskActivityPresenter");
        this.getRequestedBooks = getRequestedBooks;
        this.requestAccepted = requestAccepted;
        this.requestRejected = requestRejected;
        this.dataMapper = dataMapper;


    }


    public void setView(@NonNull MyTasksView view) {
        myTasksView = view;
    }

    public void initialize() {
        hideViewRetry();
        showViewLoading();
        myTasksView.setActionBar();
        loadBookRequestedToUser();
    }

    private void showViewLoading() {
        myTasksView.showLoading();
    }

    private void hideViewLoading() {

        myTasksView.hideLoading();
    }

    @Override
    public void retry() {
        hideViewRetry();
        showViewLoading();
        loadBookRequestedToUser();

    }

    private void showViewRetry() {
        myTasksView.showRetry();
    }

    private void hideViewRetry() {
        myTasksView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(myTasksView.context(),
                errorBundle.getException());
        myTasksView.showError(errorMessage);
    }


    @Override
    public void onClickOk(BooksRequestedToUserModel bookRequested, int position) {
        requestAccepted.execute(2, new RequestStatusSubscriber(position));
    }

    @Override
    public void onCLickCancel(BooksRequestedToUserModel bookRequested, int position) {
        requestRejected.execute(2, new RequestStatusSubscriber(position));
    }

    @Override
    public void loadBookRequestedToUser() {
        getRequestedBooks.execute(new MyTasksSubscriber());
    }

    private void showSearchBookRequestedResults(List<BooksRequestedToUser> bookRequests) {

        final Collection<BooksRequestedToUserModel> bookRequestModelsCollection = this.dataMapper.transformRequestedBooks(bookRequests);
        myTasksView.renderMyTasks(bookRequestModelsCollection);
        myTasksView.updateActionBar();

    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        requestRejected.unsubscribe();
        requestAccepted.unsubscribe();
        getRequestedBooks.unsubscribe();
        myTasksView = null;
    }

    private final class MyTasksSubscriber extends DefaultSubscriber<List<BooksRequestedToUser>> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showSearchBookRequestedResults(null);

        }

        @Override
        public void onNext(List<BooksRequestedToUser> books) {
            hideViewLoading();
            showSearchBookRequestedResults(books);
        }
    }

    private final class RequestStatusSubscriber extends DefaultSubscriber<Boolean> {

        int itemPositionInAdapter;

        RequestStatusSubscriber(int position) {
            itemPositionInAdapter = position;
        }

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            myTasksView.displaySnackBar(true);

        }

        @Override
        public void onNext(Boolean requestSentSuccessfully) {
            hideViewLoading();
            myTasksView.removeATask(itemPositionInAdapter);
            myTasksView.updateActionBar();
            myTasksView.displaySnackBar(false);
        }
    }
}
