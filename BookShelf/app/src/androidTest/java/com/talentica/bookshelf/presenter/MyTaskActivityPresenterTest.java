package com.talentica.bookshelf.presenter;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.presentation.leadCapturePage.tasks.presenter.MyTaskActivityPresenter;
import com.talentica.presentation.leadCapturePage.tasks.view.MyTaskActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyTaskActivityPresenterTest {
    private MyTaskActivityPresenter presenter;
    private MyTaskActivityView view;

    @Before
    public void setView() {
        view = Mockito.mock(MyTaskActivityView.class);
        presenter = new MyTaskActivityPresenter();
        presenter.setView(view);

    }

    @Test
    public void myTaskActivityPresenterTest() {
        presenter.initialize();
        verify(view).setFirstFragment();

        presenter.updateActionBar(2, "dummy");
        verify(view).updateActionBar(anyInt(), anyString());

    }
}
