package com.talentica.bookshelf.presenter;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.GetBooksToAdd;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookMainPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookMainView;
import com.talentica.presentation.mapper.BookModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AddBookMainPresenterTest {

    private AddBookMainPresenter presenter;
    private AddBookMainView view;
    private BookModelDataMapper bookModelDataMapperMock;
    private GetBooksToAdd useCase1;


    @Before
    public void setView() {
        view = Mockito.mock(AddBookMainView.class);
        bookModelDataMapperMock = Mockito.mock(BookModelDataMapper.class);
        useCase1 = Mockito.mock(GetBooksToAdd.class);
        presenter = new AddBookMainPresenter(useCase1, bookModelDataMapperMock);
        presenter.setView(view);
    }


    @Test
    public void addBookMainPresenterTest() {
        presenter.initialize();

        verify(view).setActionBarItems();
        verify(view).setDiscriptionBarText();
        verify(view).enableButtonForManualDetail();

        presenter.searchBooksForQueryString("Shakti");
        verify(useCase1).execute(anyString(), any(Subscriber.class));


    }

}
