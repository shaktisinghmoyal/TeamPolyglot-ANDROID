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
import com.talentica.presentation.leadCapturePage.home.view.BooksGridView;
import com.talentica.presentation.mapper.DataMapper;
import com.talentica.presentation.utils.Enums;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class BooksGridViewPresenter implements IBooksGridViewPresenter, Presenter {
    private final String Tag = "BooksGridViewPresenter";
    private final BaseUseCase getBooksGridResultUseCase;
    private final BaseUseCase getRecentlyAddedUseCase;
    private final BaseUseCase getMostReadUseCase;
    private final DataMapper bookModelDataMapper;
    private BooksGridView booksGridView;

    @Inject
    public BooksGridViewPresenter(@Named("getBookGridResultsUseCase") BaseUseCase getBooksGridResultUseCase, @Named("recentlyAddedBookList") BaseUseCase getRecentlyAddedUseCase, @Named("mostReadBookList") BaseUseCase getMostReadUseCase, DataMapper bookModelDataMapper) {
        this.getBooksGridResultUseCase = getBooksGridResultUseCase;
        this.getRecentlyAddedUseCase = getRecentlyAddedUseCase;
        this.getMostReadUseCase = getMostReadUseCase;
        this.bookModelDataMapper = bookModelDataMapper;
    }

    public void setView(@NonNull BooksGridView view) {
        Log.e(Tag, "" + "setView  " + view);
        booksGridView = view;
    }

    public void initialize(Enums.gridViewType SearchType) {
        hideViewRetry();
        showViewLoading();
        setActionBar();
        loadBookSearchResults(SearchType);
        setFragmentPageTitle();

    }


    private void showViewLoading() {
        booksGridView.showLoading();
    }

    private void hideViewLoading() {

        booksGridView.hideLoading();
    }

    @Override
    public void retry(Enums.gridViewType SearchType) {
        hideViewRetry();
        showViewLoading();
        loadBookSearchResults(SearchType);

    }

    private void showViewRetry() {
        booksGridView.showRetry();
    }

    private void hideViewRetry() {
        booksGridView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(booksGridView.context(),
                errorBundle.getException());
        booksGridView.showError(errorMessage);
    }


    @Override
    public void loadBookSearchResults(Enums.gridViewType SearchType) {
        if (SearchType == Enums.gridViewType.DIRECT_SEARCH) {
        getBooksGridResultUseCase.execute(new SearchBookResultsSubscriber());
        } else if (SearchType == Enums.gridViewType.RECENTLY_ADDED) {
            getRecentlyAddedUseCase.execute(new SearchBookResultsSubscriber());
        } else {
            getMostReadUseCase.execute(new SearchBookResultsSubscriber());
        }

    }

    @Override
    public void setActionBar() {
        booksGridView.setActionBar();
    }

    @Override
    public void setFragmentPageTitle() {
        booksGridView.setFragmentTitle();
    }

    @Override
    public void onGridViewBookClick(BookModel bookModel) {
        booksGridView.viewBook(bookModel);
    }

    private void showSearchBookResults(List<Book> books) {

        final Collection<BookModel> bookModelsCollection = this.bookModelDataMapper.transform(books);
        booksGridView.showSearchBookResults(bookModelsCollection);

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getBooksGridResultUseCase.unsubscribe();
        booksGridView = null;
    }

    private final class SearchBookResultsSubscriber extends DefaultSubscriber<List<Book>> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));

        }

        @Override
        public void onNext(List<Book> books) {
            hideViewLoading();
            showSearchBookResults(books);
        }
    }
}
