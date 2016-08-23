package com.talentica.bookshelf.presenter;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.GetNotifications;
import com.talentica.presentation.leadCapturePage.notifications.presenter.NotificationPresenterImpl;
import com.talentica.presentation.leadCapturePage.notifications.view.NotificationView;
import com.talentica.presentation.mapper.DataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NotificationPresenterTest {

    private NotificationPresenterImpl presenter;
    private DataMapper mapper;
    private NotificationView view;
    private GetNotifications useCase1;


    @Before
    public void setView() {

        view = Mockito.mock(NotificationView.class);
        useCase1 = Mockito.mock(GetNotifications.class);

        mapper = Mockito.mock(DataMapper.class);

        presenter = new NotificationPresenterImpl(useCase1, mapper);
        presenter.setView(view);
    }


    @Test
    public void getNotificationTest() {
        presenter.initialize();
        verify(view).hideRetry();
        verify(view).showLoading();
        verify(view).setActionBar();
        verify(useCase1).execute(any(Subscriber.class));
    }
}
