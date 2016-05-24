package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.model.Book;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.HomeView;
import com.talentica.presentation.mapper.BookModelDataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class HomePagePresenter implements IHomePagePresenter, Presenter {


    private final BaseUseCase getBookListUseCase;
    private final BookModelDataMapper bookModelDataMapper;
    private HomeView homeView;

    @Inject
    public HomePagePresenter(@Named("bookList") BaseUseCase getBookListUseCase, BookModelDataMapper bookModelDataMapper) {
        this.getBookListUseCase = getBookListUseCase;
        this.bookModelDataMapper = bookModelDataMapper;

    }

    public void setView(@NonNull HomeView view) {
        homeView = view;
    }

    /**
     * Initializes the presenter by start retrieving the book list.
     */
    public void initialize() {
        loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        hideViewRetry();
        showViewLoading();
        getBookList();
    }

    private void showViewLoading() {
        homeView.showLoading();
    }

    private void hideViewLoading() {
        homeView.hideLoading();
    }

    private void showViewRetry() {
        homeView.showRetry();
    }

    private void hideViewRetry() {
        homeView.hideRetry();
    }

    private void getBookList() {
        Log.e("getBookList", "called");
        this.getBookListUseCase.execute(new BookListSubscriber());
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(homeView.context(),
                errorBundle.getException());
        this.homeView.showError(errorMessage);
    }

    private void showBooksCollectionInView(Collection<Book> usersCollection) {
        final Collection<BookModel> bookModelsCollection =
                this.bookModelDataMapper.transform(usersCollection);
        homeView.displayRecentlyAddedBooks(bookModelsCollection);
    }

    @Override
    public void loadRecentlyAddedBooks() {

    }

    @Override
    public void loadMostReadBooks() {

    }

    @Override
    public void loadNextRecentlyAddedBooksOnSwipe() {

    }

    @Override
    public void loadNextMostReadBooksOnSwipe() {

    }

    @Override
    public void onSearch(String string) {

    }

    @Override
    public void onBookClick() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getBookListUseCase.unsubscribe();
        this.homeView = null;
    }

    private final class BookListSubscriber extends DefaultSubscriber<List<Book>> {

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }

        @Override
        public void onNext(List<Book> books) {

            showBooksCollectionInView(books);
        }
    }
}
