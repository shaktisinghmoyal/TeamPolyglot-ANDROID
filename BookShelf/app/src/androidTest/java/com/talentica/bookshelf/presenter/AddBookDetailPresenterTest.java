package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.model.Book;
import com.talentica.domain.usecases.SubmitMyBook;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookDetailPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookDetailView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.mapper.BookModelDataMapper;
import com.talentica.presentation.utils.Util;

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
public class AddBookDetailPresenterTest {

    private AddBookDetailPresenter presenter;
    private AddBookDetailView view;
    private BookModelDataMapper bookModelDataMapper;
    private SubmitMyBook submitMyBookUseCase;

    @Before
    public void setView() {
        view = Mockito.mock(AddBookDetailView.class);
        submitMyBookUseCase = Mockito.mock(SubmitMyBook.class);
        bookModelDataMapper = Mockito.mock(BookModelDataMapper.class);

        presenter = new AddBookDetailPresenter(submitMyBookUseCase, bookModelDataMapper);
        presenter.setView(view);
    }


    @Test
    public void presenterAutoPageTest() {

        presenter.initialize(Util.AUTO_FILL_DETAIL);
        verify(view).setActionBarItems();
        verify(view).setDiscriptionBarText(anyInt());
        verify(view).enableAutoFilledPart();

        presenter.addBookToServer(any(BookModel.class));
        verify(submitMyBookUseCase).execute(any(Book.class), any(Subscriber.class));
    }

    @Test
    public void presenterManualTest() {

        presenter.initialize(Util.MANUALLY_FILL_DETAIL);
        verify(view).setActionBarItems();
        verify(view).setDiscriptionBarText(anyInt());
        verify(view).enableManualFilledPart();

        presenter.addBookToServer(any(BookModel.class));
        verify(submitMyBookUseCase).execute(any(Book.class), any(Subscriber.class));

    }

}
