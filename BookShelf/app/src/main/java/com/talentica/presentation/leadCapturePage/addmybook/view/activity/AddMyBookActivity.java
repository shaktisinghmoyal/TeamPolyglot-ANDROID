package com.talentica.presentation.leadCapturePage.addmybook.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.talentica.R;
import com.talentica.databinding.AddMyBookActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.AddBookComponent;
import com.talentica.presentation.internal.di.components.DaggerAddBookComponent;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookActivityPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddMyBookActivityView;
import com.talentica.presentation.leadCapturePage.addmybook.view.fragment.AddBookDetailFragment;
import com.talentica.presentation.leadCapturePage.addmybook.view.fragment.AddBookMainFragment;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;

public class AddMyBookActivity extends BaseActivity implements AddMyBookActivityView, HasComponent<AddBookComponent>, AddBookMainFragment.FragmentButtonListner, AddBookDetailFragment.FragmentButtonListner {
    public static Activity activity;
    private final String Tag = "AddMyBookActivity";
    @Inject
    public AddBookActivityPresenter addBookActivityPresenter;
    private AddMyBookActivityBinding addMyBookActivityBinding;
    private AddBookComponent addBookComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addMyBookActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.add_my_book_activity);
        initializeInjector();
        activity = addBookComponent.activity();
        addBookComponent.inject(this);

        addBookActivityPresenter.setView(this);
        addBookActivityPresenter.initialize(savedInstanceState);

    }


    @Override
    public void setFirstFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            navigator.addFragment(this, R.id.add_book_activity_fragment_container, new AddBookMainFragment());
        }

    }

    private void initializeInjector() {
        addBookComponent = DaggerAddBookComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    @Override
    public void setActionBar() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.action_bar_color_string))));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setDiscriptionBar(int discriptionId, int stepId) {
        addMyBookActivityBinding.pageDiscription.setText(discriptionId);
        addMyBookActivityBinding.stepText.setText(stepId);
    }

    @Override
    public AddBookComponent getComponent() {
        return addBookComponent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onEnterManuallyButtonClicked() {
        Log.e(Tag, "onEnterManuallyButtonClicked");
        Bundle typeOfFillDetail = new Bundle();
        typeOfFillDetail.putInt(Util.fillDetailType, Util.MANUALLY_FILL_DETAIL);
        AddBookDetailFragment fragment = new AddBookDetailFragment();
        fragment.setArguments(typeOfFillDetail);
        navigator.addFragment(this, R.id.add_book_activity_fragment_container, fragment, Util.ADD);
    }


    @Override
    public void onNextButtonClicked(BookModel bookModel) {
        Log.e(Tag, "onNextButtonClicked , bundle is = " + bookModel);
        Bundle typeOfFillDetail = new Bundle();
        typeOfFillDetail.putParcelable(Util.bookDetailBundle, bookModel);
        typeOfFillDetail.putInt(Util.fillDetailType, Util.AUTO_FILL_DETAIL);
        AddBookDetailFragment fragment = new AddBookDetailFragment();
        fragment.setArguments(typeOfFillDetail);
        navigator.addFragment(this, R.id.add_book_activity_fragment_container, fragment, Util.ADD);
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


    @Override
    public void onBackPressed() {
        Log.e(Tag, "" + "onBackPressed");
        super.onBackPressed();

    }


    @Override
    public void onBackButtonClicked() {
        Log.e(Tag, "" + "onBackButtonClicked");
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(addMyBookActivityBinding.addBookActivityFragmentContainer.getId());
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
