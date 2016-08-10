package com.talentica.presentation.leadCapturePage.home.view.acitivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.talentica.R;
import com.talentica.databinding.ListAllActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.DaggerLeadCaptureComponent;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.ListAllActivityPresenter;
import com.talentica.presentation.leadCapturePage.home.view.ListAllView;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BooksGridViewFragment;
import com.talentica.presentation.utils.GridViewItemClickListnerInterface;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;


public class ListAllActivity extends BaseActivity implements ListAllView, HasComponent<LeadCaptureComponent>, GridViewItemClickListnerInterface {
    private final String Tag = "ListAllActivity";
    @Inject
    ListAllActivityPresenter listAllActivityPresenter;
    private LeadCaptureComponent leadCaptureComponent;
    private ListAllActivityBinding listAllActivityBinding;
    private Bundle fragmentDetailBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentDetailBundle = getIntent().getBundleExtra(Util.fragmentTitle);
        listAllActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.list_all_activity);
        initializeInjector();
        leadCaptureComponent.inject(this);
        listAllActivityPresenter.setView(this);
        listAllActivityPresenter.initialize();

    }


    private void initializeInjector() {
        this.leadCaptureComponent = DaggerLeadCaptureComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }


    @Override
    public void addGridViewFragment() {
        BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
        booksGridViewFragment.setArguments(fragmentDetailBundle);
        navigator.addFragment(this, R.id.book_grid_fragment_container, booksGridViewFragment);
    }

    @Override
    public LeadCaptureComponent getComponent() {
        return leadCaptureComponent;
    }

    @Override
    public void onGridViewBookClicked(BookModel bookModel) {
        Bundle bookDetailBundle = new Bundle();

        bookDetailBundle.putParcelable(Util.bookDetailBundle, bookModel);
        navigator.startBookDetailActivity(this, bookDetailBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        listAllActivityPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        listAllActivityPresenter.pause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        listAllActivityPresenter.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


