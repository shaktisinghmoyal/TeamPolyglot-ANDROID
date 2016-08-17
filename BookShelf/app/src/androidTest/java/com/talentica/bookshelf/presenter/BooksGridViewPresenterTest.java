package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.GetBooksGridResults;
import com.talentica.domain.usecases.GetMostReadBookList;
import com.talentica.domain.usecases.GetRecentlyAddedBookList;
import com.talentica.presentation.leadCapturePage.home.presenter.BooksGridViewPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BooksGridView;
import com.talentica.presentation.mapper.BookModelDataMapper;
import com.talentica.presentation.utils.Enums;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BooksGridViewPresenterTest {

    private BooksGridViewPresenter presenter;
    private BookModelDataMapper mapper;
    private BooksGridView view;
    private GetRecentlyAddedBookList useCase1;
    private GetMostReadBookList useCase2;
    private GetBooksGridResults useCase3;

    @Before
    public void setView() {

        view = Mockito.mock(BooksGridView.class);
        useCase1 = Mockito.mock(GetRecentlyAddedBookList.class);
        useCase2 = Mockito.mock(GetMostReadBookList.class);
        useCase3 = Mockito.mock(GetBooksGridResults.class);
        mapper = Mockito.mock(BookModelDataMapper.class);

        presenter = new BooksGridViewPresenter(useCase3, useCase1, useCase2, mapper);
        presenter.setView(view);
    }


    @Test
    public void gridViewPresenterMostReadTest() {
        presenter.initialize(Enums.gridViewType.MOST_READ);
        verify(view).hideRetry();
        verify(view).showLoading();
        verify(view).setActionBar();
        verify(useCase2).execute(any(Subscriber.class));
        verify(view).setFragmentTitle();

    }

    @Test
    public void gridViewPresenterRecentlyAddedTest() {
        presenter.initialize(Enums.gridViewType.RECENTLY_ADDED);
        verify(view).hideRetry();
        verify(view).showLoading();
        verify(view).setActionBar();
        verify(useCase1).execute(any(Subscriber.class));
        verify(view).setFragmentTitle();

    }

    @Test
    public void gridViewPresenterDirectSearchTest() {
        presenter.initialize(Enums.gridViewType.DIRECT_SEARCH);
        verify(view).hideRetry();
        verify(view).showLoading();
        verify(view).setActionBar();
        verify(useCase3).execute(any(Subscriber.class));
        verify(view).setFragmentTitle();

    }
}
