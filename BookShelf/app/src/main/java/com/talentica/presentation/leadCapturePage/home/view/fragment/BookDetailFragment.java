package com.talentica.presentation.leadCapturePage.home.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.talentica.R;
import com.talentica.databinding.BookLayoutBinding;
import com.talentica.presentation.internal.di.components.BookDetailComponent;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.BookPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BookView;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.BookDetailActivity;
import com.talentica.presentation.utils.ConfirmationButtonsClickInterface;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;

public class BookDetailFragment extends BaseFragment implements BookView, ConfirmationButtonsClickInterface {


    private final String Tag = "BookFragment";
    @Inject
    BookPresenter bookPresenter;
    private BookLayoutBinding bookLayoutBinding;
    private ColorDrawable colorDrawableOpaqueGrey;
    private ColorDrawable colorDrawableOpaqueBlack;
    private ColorDrawable removeColorDrawableOpaqueGrey;
    private ColorDrawable removeColorDrawableOpaqueBlack;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(BookDetailComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bookLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.book_layout, container, false);

        return bookLayoutBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //searchSuggestionPresenter.setView(this);
        BookModel bookModel = getArguments().getParcelable(Util.bookDetailBundle);
        bookPresenter.setView(this);
        if (savedInstanceState == null) {
            initializeBook(bookModel);
        }

        intitializeOpacity();
        initializeSnackBar();




    }

    private void intitializeOpacity() {
        colorDrawableOpaqueGrey = new ColorDrawable(Color.parseColor("#181818"));
        removeColorDrawableOpaqueGrey = new ColorDrawable(Color.parseColor("#E8E8E8"));
        ;
        colorDrawableOpaqueGrey.setAlpha(75);

        colorDrawableOpaqueBlack = new ColorDrawable(Color.parseColor("#424242"));
        removeColorDrawableOpaqueBlack = new ColorDrawable(Color.parseColor("#424242"));
        ;
        colorDrawableOpaqueBlack.setAlpha(180);

    }


    private void initializeSnackBar() {
        coordinatorLayout = bookLayoutBinding.borrowRequestSnackBar;
    }

    private void initializeBook(BookModel bookModel) {

        bookPresenter.initialize(bookModel);
    }

    @Override
    public void showBookWithDetail(BookModel bookModel) {
        bookLayoutBinding.setBookModel(bookModel);
    }
    @Override
    public void displaySnackBar() {
        snackbar = Snackbar
                .make(coordinatorLayout, "Request sent successfully!", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackground(new ColorDrawable(Color.parseColor("#00BE7D")));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }

    @Override
    public void setActionSearchBar() {
        ((BookDetailActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.action_bar_color_string))));
        ((BookDetailActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BookDetailActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.book_detail_title));
    }

    @Override
    public void onButtonClickedForSnackBar() {
        bookPresenter.renderSnackBar();
    }

    @Override
    public void setCustomViewOpaque() {
        Log.e(Tag, "setCustomViewOpaque ");

        bookLayoutBinding.customView2.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView3.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView4.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView5.setBackground(colorDrawableOpaqueBlack);
        bookLayoutBinding.customView6.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView7.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView8.setBackground(colorDrawableOpaqueBlack);
        bookLayoutBinding.customView9.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView10.setBackground(colorDrawableOpaqueBlack);
        bookLayoutBinding.customView11.setBackground(colorDrawableOpaqueGrey);
        bookLayoutBinding.customView12.setBackground(colorDrawableOpaqueGrey);
    }

    @Override
    public void removeCustomViewOpacity() {
        Log.e(Tag, "removeCustomViewOpacity");

        bookLayoutBinding.customView2.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView3.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView4.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView5.setBackground(removeColorDrawableOpaqueBlack);
        bookLayoutBinding.customView6.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView7.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView8.setBackground(removeColorDrawableOpaqueBlack);
        bookLayoutBinding.customView9.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView10.setBackground(removeColorDrawableOpaqueBlack);
        bookLayoutBinding.customView11.setBackground(removeColorDrawableOpaqueGrey);
        bookLayoutBinding.customView12.setBackground(removeColorDrawableOpaqueGrey);
    }

    @Override
    public void onResume() {
        super.onResume();
        bookPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bookPresenter.pause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bookPresenter.destroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        bookPresenter = null;
    }


    @Override
    public void onButtonClickedForOpacity() {
        Log.e(Tag, "onButtonClickedForOpacity");
        bookPresenter.enableOpacity();

    }

    @Override
    public void onButtonCLickToRemoveOpacity() {
        Log.e(Tag, "onButtonCLickToRemoveOpacity");
        bookPresenter.disableOpacity();
    }
}
