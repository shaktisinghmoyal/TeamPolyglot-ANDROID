package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.presentation.leadCapturePage.notifications.presenter.NotifiActivityPresenterImpl;
import com.talentica.presentation.leadCapturePage.notifications.view.NotificationActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NotificationActivityPresenterTest {
    private NotifiActivityPresenterImpl presenter;
    private NotificationActivityView view;

    @Before
    public void setView() {
        view = Mockito.mock(NotificationActivityView.class);
        presenter = new NotifiActivityPresenterImpl();
        presenter.setView(view);

    }

    @Test
    public void notificationActivityPresenterTest() {
        presenter.initialize();
        verify(view).setFirstFragment();

        presenter.setActionBar(2);
        verify(view).setActionBar(anyInt());

    }
}
