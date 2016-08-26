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
import com.talentica.presentation.mapper.DataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class HomePagePresenter implements IHomePagePresenter, Presenter {

    private final String Tag = "HomePagePresenter";
    private final BaseUseCase getRecentlyAddedBookListUseCase;
    private final BaseUseCase getMostReadBookListUseCase;
    private final DataMapper bookModelDataMapper;
    private final int RECENT_ADDED_ON_SWIPE = 3;
    private final int MOST_READ_ON_SWIPE = 4;
    private final int RECENT_ADDED_BOOKS_QUERY = 1;
    private final int MOST_READ_BOOK_QUERY = 2;
    private final int BOTH_BOOK_QUERY = 0;
    private HomeView homeView;

    @Inject
    public HomePagePresenter(@Named("recentlyAddedBookList") BaseUseCase getRecentlyAddedBookListUseCase, @Named("mostReadBookList") BaseUseCase getMostReadBookListUseCase, DataMapper bookModelDataMapper) {
        Log.e(Tag, "getRecentlyAddedBookListUseCase");
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
        hideViewRetry(BOTH_BOOK_QUERY);
        showViewLoading(BOTH_BOOK_QUERY);
        homeView.setBottomBarIconForHome();
        loadRecentlyAddedBooks();
        loadMostReadBooks();
    }

    /**
     * Loads all users.
     */
    public void loadNextBooksList(int queryType) {
        Log.e(Tag, "loadNextBooksList " + queryType);
        hideViewRetry(queryType);
        getNextBookList(queryType);
    }

    public void tryAgain(int queryType) {
        Log.e(Tag, "tryAgain " + queryType);
        hideViewRetry(queryType);
        if (queryType == 1) {
            loadRecentlyAddedBooks();
        } else {
            loadMostReadBooks();
        }
    }
    private void setActionBar() {
        homeView.setActionSearchBar();
    }

    private void showViewLoading(int queryType) {
        Log.e(Tag, "showViewLoading " + queryType);
        if (queryType == 1) {
            showViewLoading1();
        } else if (queryType == 2) {
            showViewLoading2();
        } else {
            showViewLoading1();
            showViewLoading2();
        }

    }

    private void showViewLoading1() {
        homeView.showLoadingRecycler1();
    }

    private void showViewLoading2() {
        homeView.showLoadingRecycler2();
    }


    private void hideViewLoading(int queryType) {
        Log.e(Tag, "hideViewLoading " + queryType);
        if (queryType == 1) {
            hideViewLoading1();
        } else if (queryType == 2) {
            hideViewLoading2();
        } else {
            hideViewLoading1();
            hideViewLoading2();
        }
    }

    private void hideViewLoading1() {
        homeView.hideLoadingRecycler1();
    }

    private void hideViewLoading2() {
        homeView.hideLoadingRecycler2();
    }


    private void showViewRetry(int queryType) {
        Log.e(Tag, "showViewRetry " + queryType);
        if (queryType == 0) {
            showViewRetry1();
            showViewRetry2();

        } else if (queryType == 1) {
            showViewRetry1();
        } else if (queryType == 2) {
            showViewRetry2();
        }
    }

    private void showViewRetry1() {
        homeView.showRetryRecycler1();
    }

    private void showViewRetry2() {
        homeView.showRetryRecycler2();
    }


    private void hideViewRetry(int queryType) {
        Log.e(Tag, "hideViewRetry " + queryType);
        if (queryType == 1) {
            hideViewRetry1();
        } else if (queryType == 2) {
            hideViewRetry2();
        } else {
            hideViewRetry1();
            hideViewRetry2();
        }
    }

    private void hideViewRetry1() {
        homeView.hideRetryRecycler1();
    }

    private void hideViewRetry2() {
        homeView.hideRetryRecycler2();
    }

    private void getNextBookList(int typeOfBooks) {
        Log.e(Tag, "getNextBookList " + typeOfBooks);
        if (typeOfBooks == RECENT_ADDED_BOOKS_QUERY) {
            loadNextRecentlyAddedBooksOnSwipe();
        } else if (typeOfBooks == MOST_READ_BOOK_QUERY) {
            loadNextMostReadBooksOnSwipe();
        }

    }

    private void showErrorMessage(ErrorBundle errorBundle, int queryType) {
        Log.e(Tag, "showErrorMessage " + queryType);
        if (queryType == 0) {
            showErrorMessage1(errorBundle);
            showErrorMessage2(errorBundle);

        } else if (queryType == 1) {
            showErrorMessage1(errorBundle);
        } else if (queryType == 2) {
            showErrorMessage2(errorBundle);
        }
    }


    private void showErrorMessage1(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(homeView.context(),
                errorBundle.getException());
        this.homeView.showErrorRecycler1(errorMessage);
    }

    private void showErrorMessage2(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(homeView.context(),
                errorBundle.getException());
        this.homeView.showErrorRecycler2(errorMessage);
    }

    private void showBooksCollectionInView(Collection<Book> usersCollection, int queryNo) {
        Log.e(Tag, "showBooksCollectionInView " + queryNo);
        final Collection<BookModel> bookModelsCollection =
                this.bookModelDataMapper.transform(usersCollection);
        if (queryNo == 1 || queryNo == 3) {
            homeView.displayRecentlyAddedBooks(bookModelsCollection);
        } else {
            homeView.displayMostReadBooks(bookModelsCollection);
        }

    }

    @Override
    public void loadRecentlyAddedBooks() {
        Log.e(Tag, "loadRecentlyAddedBooks ");
        getRecentlyAddedBookListUseCase.execute(new BookListSubscriber(RECENT_ADDED_BOOKS_QUERY));

    }

    @Override
    public void loadMostReadBooks() {
        Log.e(Tag, "loadMostReadBooks ");
        getMostReadBookListUseCase.execute(new BookListSubscriber(MOST_READ_BOOK_QUERY));

    }

    @Override
    public void loadNextRecentlyAddedBooksOnSwipe() {
        Log.e(Tag, "loadNextRecentlyAddedBooksOnSwipe ");
        getRecentlyAddedBookListUseCase.execute(new BookListSubscriber(RECENT_ADDED_ON_SWIPE));

    }

    @Override
    public void loadNextMostReadBooksOnSwipe() {
        Log.e(Tag, "loadNextMostReadBooksOnSwipe ");
        getMostReadBookListUseCase.execute(new BookListSubscriber(MOST_READ_ON_SWIPE));

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
            hideViewLoading(queryNo);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(Tag, "onError " + queryNo);
            hideViewLoading(queryNo);
            showErrorMessage(new DefaultErrorBundle((Exception) e), queryNo);
            showViewRetry(queryNo);
        }

        @Override
        public void onNext(List<Book> books) {
            Log.e(Tag, "onNext " + queryNo);
            hideViewLoading(queryNo);
            showBooksCollectionInView(books, queryNo);
        }
    }
}
