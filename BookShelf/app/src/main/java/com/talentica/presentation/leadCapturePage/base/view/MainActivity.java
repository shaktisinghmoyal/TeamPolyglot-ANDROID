package com.talentica.presentation.leadCapturePage.base.view;

import android.os.Bundle;

import com.talentica.R;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.BookComponent;
import com.talentica.presentation.internal.di.components.DaggerBookComponent;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.HomeFragment;

public class MainActivity extends BaseActivity implements LeadCapturePageView, HasComponent<BookComponent>, HomeFragment.BookListListener {

    private BookComponent bookComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.main_fragment_container, new HomeFragment());
        }

    }

    private void initializeInjector() {
        this.bookComponent = DaggerBookComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public void setTitle() {

    }

    @Override
    public void setBottomMenu() {

    }

    @Override
    public void displayNotificationCount() {

    }


    @Override
    public void onBookClicked(BookModel book) {

    }

    @Override
    public BookComponent getComponent() {
        return bookComponent;
    }
}
