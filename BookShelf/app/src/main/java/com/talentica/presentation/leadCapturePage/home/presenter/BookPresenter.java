package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;

import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.BookView;

import javax.inject.Inject;

@PerActivity
public class BookPresenter implements IBookPresenter, Presenter {

    private final String Tag = "BookPresenter";
    private BookView bookView;

    @Inject
    public BookPresenter() {

    }


    public void setView(@NonNull BookView view) {
        bookView = view;
    }

    public void initialize(BookModel bookModel) {

        setActionBar();
        loadBookWithDetail(bookModel);


    }

    private void setActionBar() {
        bookView.setActionSearchBar();
    }

    @Override
    public void loadBookWithDetail(BookModel bookModel) {
        bookView.showBookWithDetail(bookModel);
    }

    @Override
    public void enableOpacity() {
        bookView.setCustomViewOpaque();
    }

    @Override
    public void renderSnackBar() {
        bookView.displaySnackBar();
    }

    @Override
    public void disableOpacity() {
        bookView.removeCustomViewOpacity();
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
