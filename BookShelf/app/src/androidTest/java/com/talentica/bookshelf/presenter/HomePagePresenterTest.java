package com.talentica.bookshelf.presenter;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.talentica.domain.usecases.GetMostReadBookList;
import com.talentica.domain.usecases.GetRecentlyAddedBookList;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;
import com.talentica.presentation.leadCapturePage.home.presenter.HomePagePresenter;
import com.talentica.presentation.leadCapturePage.home.view.HomeView;
import com.talentica.presentation.mapper.BookModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomePagePresenterTest {


    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private HomePagePresenter homePagePresenter;
    private Context mockContext;
    private HomeView homeViewMock;
    private GetMostReadBookList getMostReadBookListMock;
    private GetRecentlyAddedBookList getRecentlyAddedBookListMock;
    private BookModelDataMapper bookModelDataMapperMock;

    @Before
    public void setView() {
        // MockitoAnnotations.initMocks(this);
        homeViewMock = Mockito.mock(HomeView.class);
        getMostReadBookListMock = Mockito.mock(GetMostReadBookList.class);
        getRecentlyAddedBookListMock = Mockito.mock(GetRecentlyAddedBookList.class);
        bookModelDataMapperMock = Mockito.mock(BookModelDataMapper.class);
        mockContext = Mockito.mock(Context.class);
        //  System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
        homePagePresenter = new HomePagePresenter(getRecentlyAddedBookListMock, getMostReadBookListMock, bookModelDataMapperMock);
        homePagePresenter.setView(homeViewMock);


    }

    @Test
    public void setHomePagePresenterTest() {


        given(homeViewMock.context()).willReturn(mockContext);

        homePagePresenter.initialize();

        verify(homeViewMock).hideRetry();
        verify(homeViewMock).showLoading();
        verify(getRecentlyAddedBookListMock).execute(any(Subscriber.class));
        verify(getMostReadBookListMock).execute(any(Subscriber.class));
    }
}
