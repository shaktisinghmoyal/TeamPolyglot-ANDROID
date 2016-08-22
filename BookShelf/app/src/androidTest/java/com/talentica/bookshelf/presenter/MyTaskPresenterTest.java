package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.BookRequestAccepted;
import com.talentica.domain.usecases.BookRequestRejected;
import com.talentica.domain.usecases.GetTasks;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;
import com.talentica.presentation.leadCapturePage.tasks.presenter.MyTasksPresenter;
import com.talentica.presentation.leadCapturePage.tasks.view.MyTasksView;
import com.talentica.presentation.mapper.DataMapper;

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
public class MyTaskPresenterTest {
    private MyTasksPresenter presenter;
    private DataMapper mapper;
    private MyTasksView view;
    private GetTasks useCase1;
    private BookRequestAccepted useCase2;
    private BookRequestRejected useCase3;

    @Before
    public void setView() {

        view = Mockito.mock(MyTasksView.class);
        useCase1 = Mockito.mock(GetTasks.class);
        useCase2 = Mockito.mock(BookRequestAccepted.class);
        useCase3 = Mockito.mock(BookRequestRejected.class);
        mapper = Mockito.mock(DataMapper.class);

        presenter = new MyTasksPresenter(useCase1, useCase2, useCase3, mapper);
        presenter.setView(view);
    }


    @Test
    public void getTasksTest() {
        presenter.initialize();
        verify(view).hideRetry();
        verify(view).showLoading();
        verify(view).updateActionBar();
        verify(useCase1).execute(any(Subscriber.class));
    }


    @Test
    public void bookRequestAcceptedTest() {
        presenter.onClickOk(new BooksRequestedToUserModel(), 5);

        verify(useCase2).execute(anyInt(), any(Subscriber.class));
    }

    @Test
    public void bookRequestRejectedTest() {
        presenter.onCLickCancel(new BooksRequestedToUserModel(), 5);

        verify(useCase3).execute(anyInt(), any(Subscriber.class));
    }
}
