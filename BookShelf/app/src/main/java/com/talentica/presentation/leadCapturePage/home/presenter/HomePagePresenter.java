package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.R;
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

    private final String Tag = "HomePagePresenter";
    private final BaseUseCase getRecentlyAddedBookListUseCase;
    private final BaseUseCase getMostReadBookListUseCase;
    private final BookModelDataMapper bookModelDataMapper;
    private final int RECENT_ADDED_BOOKS_QUERY = 1;
    private final int MOST_READ_BOOK_QUERY = 2;
    private HomeView homeView;

    @Inject
    public HomePagePresenter(@Named("recentlyAddedBookList") BaseUseCase getRecentlyAddedBookListUseCase, @Named("mostReadBookList") BaseUseCase getMostReadBookListUseCase, BookModelDataMapper bookModelDataMapper) {
        Log.e("HomePagePresenter", "getRecentlyAddedBookListUseCase");
        this.getRecentlyAddedBookListUseCase = getRecentlyAddedBookListUseCase;
        this.getMostReadBookListUseCase = getMostReadBookListUseCase;
        this.bookModelDataMapper = bookModelDataMapper;

    }

    public void setView(@NonNull HomeView view) {
        homeView = view;
    }

    /**
     * Initializes the presenter by start retrieving the book list.
     */
    public void initialize() {
        setActionBar();
        hideViewRetry();
        showViewLoading();
        loadRecentlyAddedBooks();
        loadMostReadBooks();
    }

    /**
     * Loads all users.
     */
    public void loadNextBooksList(int i) {
        showViewLoading();
        getNextBookList(i);
    }

    private void setActionBar() {
        homeView.setActionSearchBar();
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

    private void getNextBookList(int typeOfBooks) {
        Log.e(Tag, "getNextBookList " + "called");
        if (typeOfBooks == RECENT_ADDED_BOOKS_QUERY) {
            loadNextRecentlyAddedBooksOnSwipe();
        } else if (typeOfBooks == MOST_READ_BOOK_QUERY) {
            loadNextMostReadBooksOnSwipe();
        }

    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(homeView.context(),
                errorBundle.getException());
        this.homeView.showError(errorMessage);
    }

    private void showBooksCollectionInView(Collection<Book> usersCollection, int queryNo) {
        final Collection<BookModel> bookModelsCollection =
                this.bookModelDataMapper.transform(usersCollection);
        if (queryNo == 1) {
            homeView.displayRecentlyAddedBooks(bookModelsCollection);
        } else {
            homeView.displayMostReadBooks(bookModelsCollection);
        }
    }

    @Override
    public void loadRecentlyAddedBooks() {
        getRecentlyAddedBookListUseCase.execute(new BookListSubscriber(RECENT_ADDED_BOOKS_QUERY));

    }

    @Override
    public void loadMostReadBooks() {
        getMostReadBookListUseCase.execute(new BookListSubscriber(MOST_READ_BOOK_QUERY));

    }

    @Override
    public void loadNextRecentlyAddedBooksOnSwipe() {
        getRecentlyAddedBookListUseCase.execute(new BookListSubscriber(RECENT_ADDED_BOOKS_QUERY));

    }

    @Override
    public void loadNextMostReadBooksOnSwipe() {
        getMostReadBookListUseCase.execute(new BookListSubscriber(MOST_READ_BOOK_QUERY));

    }

    @Override
    public void onSearch(String string) {

    }

    @Override
    public void onBookClick(BookModel bookModel) {
        homeView.viewBook(bookModel);
    }

    @Override
    public void recentlyAddedViewAllClicked() {
        homeView.moveToBooksGridView(R.string.recently_added_text);
    }

    @Override
    public void mostReadViewAllClicked() {
        homeView.moveToBooksGridView(R.string.most_read_text);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getRecentlyAddedBookListUseCase.unsubscribe();
        getMostReadBookListUseCase.unsubscribe();
        this.homeView = null;
    }

    private final class BookListSubscriber extends DefaultSubscriber<List<Book>> {
        int queryNo;

        public BookListSubscriber(int queryNo) {
            this.queryNo = queryNo;
        }
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

            showBooksCollectionInView(books, queryNo);
        }
    }
}
