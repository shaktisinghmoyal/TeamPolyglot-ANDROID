package com.talentica.bookshelf.presenter;

import android.os.Bundle;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookActivityPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddMyBookActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AddBookActivityPresenterTest {

    private AddBookActivityPresenter presenter;
    private AddMyBookActivityView view;
    private Bundle bundle;

    @Before
    public void setView() {
        view = Mockito.mock(AddMyBookActivityView.class);
        presenter = new AddBookActivityPresenter();
        presenter.setView(view);
        bundle = new Bundle();

    }


    @Test
    public void setPresenterTest() {

        presenter.initialize(bundle);

        verify(view).setActionBar();
        verify(view).setFirstFragment(any(Bundle.class));

    }
}
