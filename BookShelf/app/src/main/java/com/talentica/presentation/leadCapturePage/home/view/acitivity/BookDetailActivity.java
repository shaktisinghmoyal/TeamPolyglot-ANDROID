package com.talentica.presentation.leadCapturePage.home.view.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.talentica.R;
import com.talentica.databinding.BookDetailActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.BookDetailComponent;
import com.talentica.presentation.internal.di.components.DaggerBookDetailComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.presenter.BookActivityPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BookActivityView;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BookDetailFragment;
import com.talentica.presentation.utils.ConfirmationButtonsClickInterface;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;

public class BookDetailActivity extends BaseActivity implements BookActivityView, HasComponent<BookDetailComponent> {
    public static Activity activity;
    private final String Tag = "BookDetailActivity";
    @Inject
    BookActivityPresenter bookActivityPresenter;
    private Bundle bookDetailBundle;
    private BookDetailComponent bookDetailComponent;
    private BookDetailActivityBinding bookDetailActivityBinding;
    private ColorDrawable colorDrawable;
    private ColorDrawable colorDrawableOpaque;
    private View.OnClickListener buttonClickListners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.request_button:
                    bookActivityPresenter.onBorrowButtonClick();
                    break;

                case R.id.no:
                    bookActivityPresenter.onNoOptionClicked();
                    break;

                case R.id.yes:
                    bookActivityPresenter.onYesOptionCLicked(5);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookDetailBundle = getIntent().getBundleExtra(Util.bookDetailBundle);
        bookDetailActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.book_detail_activity);
        initializeItems();
        colorDrawableOpaque = new ColorDrawable(Color.parseColor("#000000"));
        colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        colorDrawableOpaque.setAlpha(75);


    }

    private void initializeItems() {
        initializeInjector();
        activity = bookDetailComponent.activity();
        bookDetailComponent.inject(this);
        bookActivityPresenter.setView(this);
        bookActivityPresenter.initialize();
        bookDetailActivityBinding.requestButton.setOnClickListener(buttonClickListners);
        bookDetailActivityBinding.no.setOnClickListener(buttonClickListners);
        bookDetailActivityBinding.yes.setOnClickListener(buttonClickListners);

    }

    private void initializeInjector() {
        this.bookDetailComponent = DaggerBookDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    @Override
    public void showConfirmationDialog() {
        Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_up);
        bookDetailActivityBinding.hiddenPanel.startAnimation(bottomUp);
        bookDetailActivityBinding.hiddenPanel.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideConfirmationDialog() {
        Animation bottomDown = AnimationUtils.loadAnimation(this,
                R.anim.bottom_down);
        bookDetailActivityBinding.hiddenPanel.startAnimation(bottomDown);
        bookDetailActivityBinding.hiddenPanel.setVisibility(View.GONE);
    }

    @Override
    public void addBookDetailFragment() {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        bookDetailFragment.setArguments(bookDetailBundle);
        navigator.addFragment(this, R.id.book_detail_fragment_container, bookDetailFragment);
    }

    @Override
    public void displayWithOpacity() {
        Log.e(Tag, "displayWithOpacity");
        Fragment bookDetailFragment = getSupportFragmentManager().findFragmentById(R.id.book_detail_fragment_container);
        if (bookDetailFragment instanceof ConfirmationButtonsClickInterface) {
            Log.e(Tag, " ConfirmationButtonsClickInterface");
            ((ConfirmationButtonsClickInterface) bookDetailFragment).onButtonClickedForOpacity();
        }

        bookDetailActivityBinding.bookDetailFragmentContainer.setBackground(colorDrawableOpaque);

    }

    @Override
    public void showRequestResultMessage() {
        Fragment bookDetailFragment = getSupportFragmentManager().findFragmentById(R.id.book_detail_fragment_container);
        if (bookDetailFragment instanceof ConfirmationButtonsClickInterface) {
            Log.e(Tag, " ConfirmationButtonsClickInterface");
            ((ConfirmationButtonsClickInterface) bookDetailFragment).onButtonClickedForSnackBar();
        }
    }

    @Override
    public void hideOpacity() {
        Log.e(Tag, "hideOpacity");
        Fragment bookDetailFragment = getSupportFragmentManager().findFragmentById(R.id.book_detail_fragment_container);
        if (bookDetailFragment instanceof ConfirmationButtonsClickInterface) {
            Log.e(Tag, " ConfirmationButtonsClickInterface");
            ((ConfirmationButtonsClickInterface) bookDetailFragment).onButtonCLickToRemoveOpacity();
        }
        bookDetailActivityBinding.bookDetailFragmentContainer.setBackground(colorDrawable);

    }

    @Override
    public void hideRequestButton() {
        bookDetailActivityBinding.requestButton.setVisibility(View.GONE);
    }

    @Override
    public void unhideRequestButton() {
        bookDetailActivityBinding.requestButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableRequestButton() {
        bookDetailActivityBinding.requestButton.setEnabled(true);
    }

    @Override
    public void disableRequestButton() {
        bookDetailActivityBinding.requestButton.setEnabled(false);
    }

    @Override
    public BookDetailComponent getComponent() {
        return bookDetailComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent returnBtn = new Intent(getApplicationContext(),
                        MainActivity.class);
                returnBtn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(returnBtn);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
