package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.RequestToBorrowBook;
import com.talentica.presentation.leadCapturePage.home.presenter.BookActivityPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BookActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BookActivityPresenterTest {

    private BookActivityPresenter presenter;
    private BookActivityView view;
    private RequestToBorrowBook useCase;

    @Before
    public void setView() {
        view = Mockito.mock(BookActivityView.class);
        useCase = Mockito.mock(RequestToBorrowBook.class);
        presenter = new BookActivityPresenter(useCase);
        presenter.setView(view);

    }


    @Test
    public void bookActivityPresenterTest() {
        presenter.initialize();

        verify(view).addBookDetailFragment();
        presenter.onYesOptionCLicked(45);
        verify(useCase).execute(anyInt(), any(Subscriber.class));
    }

}
