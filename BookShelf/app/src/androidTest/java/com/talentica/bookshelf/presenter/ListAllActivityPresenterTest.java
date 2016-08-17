package com.talentica.bookshelf.presenter;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.presentation.leadCapturePage.home.presenter.ListAllActivityPresenter;
import com.talentica.presentation.leadCapturePage.home.view.ListAllView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ListAllActivityPresenterTest {


    private ListAllActivityPresenter presenter;
    private ListAllView view;


    @Before
    public void setView() {
        view = Mockito.mock(ListAllView.class);
        presenter = new ListAllActivityPresenter();
        presenter.setView(view);
    }

    @Test
    public void listAllActivityPresenterTest() {
        presenter.initialize();
        verify(view).addGridViewFragment();
    }
}
