package com.talentica.presentation.leadCapturePage.addmybook.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.exception.DefaultErrorBundle;
import com.talentica.domain.exception.ErrorBundle;
import com.talentica.domain.model.Book;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.exception.ErrorMessageFactory;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookMainView;
import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.mapper.BookModelDataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class AddBookMainPresenter implements IAddBookMainPresenter, Presenter {
    private final String Tag = "AddBookMainPresenter";
    private final BaseUseCase getElasticBookSearchUseCase;
    private final BookModelDataMapper bookModelDataMapper;
    private AddBookMainView addBookMainView;
    private String recentQuery;

    @Inject
    public AddBookMainPresenter(@Named("getElasticBookSearchUseCase") BaseUseCase getElasticBookSearchUseCase, BookModelDataMapper bookModelDataMapper) {
        this.getElasticBookSearchUseCase = getElasticBookSearchUseCase;
        this.bookModelDataMapper = bookModelDataMapper;
    }

    public void setView(@NonNull AddBookMainView view) {
        Log.e(Tag, "" + "setView  " + view);
        addBookMainView = view;
    }

    public void initialize() {
        setActionBar();
        setFragmentPageDiscriptions();
        addBookMainView.enableButtonForManualDetail();

    }

    private void showViewLoading() {
        addBookMainView.showLoading();
    }

    private void hideViewLoading() {

        addBookMainView.hideLoading();
    }

    private void setActionBar() {
        addBookMainView.setActionBarItems();
    }

    private void setFragmentPageDiscriptions() {
        addBookMainView.setDiscriptionBarText();
    }

    @Override
    public void retry() {
        hideViewRetry();
        showViewLoading();
        searchBooksForQueryString(recentQuery);

    }

    @Override
    public void onManuallyAddDetailsButtonClicked() {
        addBookMainView.moveToManuallyFillDetailPage();
    }

    @Override
    public void onCancelButtonClicked() {
        addBookMainView.disableBottomNextButtons();
        addBookMainView.enableBottomTextBar();
        addBookMainView.removeBookOpacity();
    }

    @Override
    public void onNextButtonClicked() {
        addBookMainView.moveToAutoFillDetailPage();
    }

    private void showViewRetry() {
        addBookMainView.showRetry();
    }

    private void hideViewRetry() {
        addBookMainView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(addBookMainView.context(),
                errorBundle.getException());
        addBookMainView.showError(errorMessage);
    }

    @Override
    public void showManualAddButton() {

    }


    @Override
    public void updateSearchCount() {
        addBookMainView.updateSearchBookResultQuantity();
    }

    @Override
    public void onGridViewBookClick(BookModel bookModel) {
        addBookMainView.selectBook(bookModel);
    }

    @Override
    public void loadMoreBooks() {
        Log.e(Tag, "loadMoreBooks ");
        getElasticBookSearchUseCase.execute(recentQuery, new ElasticBookSearchSubscriber());
    }

    @Override
    public void searchBooksForQueryString(String query) {
        Log.e(Tag, "searchBooksForQueryString ");
        recentQuery = query;
        getElasticBookSearchUseCase.execute(query, new ElasticBookSearchSubscriber());

    }

    @Override
    public void showSelectBookBottomText() {
        addBookMainView.disableBottomNextButtons();
        addBookMainView.enableBottomTextBar();
    }

    @Override
    public void showNextBottomButtons() {
        addBookMainView.disableBottomTextBar();
        addBookMainView.enableBottomNextButtons();
    }

    private void showSearchBookResults(List<Book> books) {

        final Collection<BookModel> bookModelsCollection = this.bookModelDataMapper.transform(books);
        addBookMainView.showSearchBookResults(bookModelsCollection);
        updateSearchCount();
        showSelectBookBottomText();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getElasticBookSearchUseCase.unsubscribe();
        addBookMainView = null;
    }


    private final class ElasticBookSearchSubscriber extends DefaultSubscriber<List<Book>> {


        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            updateSearchCount();
        }

        @Override
        public void onNext(List<Book> books) {
            if (books != null) {
                hideViewLoading();
                showSearchBookResults(books);

            } else {
                hideViewLoading();
                showViewRetry();
                updateSearchCount();
            }


        }
    }
}
