package com.talentica.bookshelf.presenter;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.BookPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BookView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BookPresenterTest {

    private BookPresenter presenter;
    private BookView view;

    @Before
    public void setView() {
        view = Mockito.mock(BookView.class);
        presenter = new BookPresenter();
        presenter.setView(view);

    }

    @Test
    public void bookPresenterTest() {
        presenter.initialize(new BookModel());

        verify(view).setActionSearchBar();
        verify(view).showBookWithDetail(any(BookModel.class));
    }
}
