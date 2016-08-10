package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.view.BookActivityView;

import javax.inject.Inject;
import javax.inject.Named;

public class BookActivityPresenter implements IBookActivityPresenter, Presenter {
    private final String Tag = "BookActivityPresenter";
    private final BaseUseCase sendBookBorrowRequest;
    private BookActivityView bookActivityView;

    @Inject
    public BookActivityPresenter(@Named("bookBorrowRequest") BaseUseCase sendBookBorrowRequest) {
        Log.e(Tag, "BookActivityPresenter");
        this.sendBookBorrowRequest = sendBookBorrowRequest;

    }


    public void setView(@NonNull BookActivityView view) {
        bookActivityView = view;
    }


    public void initialize() {
        renderBook();
    }


    @Override
    public void renderBook() {
        bookActivityView.addBookDetailFragment();
    }

    @Override
    public void onBorrowButtonClick() {
        bookActivityView.showConfirmationDialog();
        bookActivityView.hideRequestButton();
        bookActivityView.displayWithOpacity();
    }

    @Override
    public void onYesOptionCLicked(int bookId) {
        bookActivityView.hideOpacity();
        bookActivityView.unhideRequestButton();
        bookActivityView.hideConfirmationDialog();
        bookActivityView.showRequestResultMessage();
        sendBookBorrowRequest.execute(bookId, new BorrowBookRequestListner());
    }

    @Override
    public void onNoOptionClicked() {
        bookActivityView.hideOpacity();
        bookActivityView.hideConfirmationDialog();
        bookActivityView.unhideRequestButton();

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.bookActivityView = null;
    }


    private final class BorrowBookRequestListner extends DefaultSubscriber<Boolean> {


        public BorrowBookRequestListner() {

        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Boolean isSuccess) {


        }
    }
}
