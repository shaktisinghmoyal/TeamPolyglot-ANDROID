package com.talentica.presentation.leadCapturePage.home.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.BookLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.BookPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BookView;
import com.talentica.presentation.utils.Enums;

import javax.inject.Inject;

public class BookDetailFragment extends BaseFragment implements BookView {

    private final String Tag = "BookFragment";
    @Inject
    BookPresenter bookPresenter;
    private BookLayoutBinding bookLayoutBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
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
        BookModel bookModel = getArguments().getParcelable(MainActivity.bookDetail);
        bookPresenter.setView(this);
        if (savedInstanceState == null) {
            initializeBooksList(bookModel);
        }

    }


    private void initializeBooksList(BookModel bookModel) {
        bookPresenter.initialize(bookModel);
    }

    @Override
    public void showBookWithDetail(BookModel bookModel) {
        bookLayoutBinding.setBookModel(bookModel);
    }

    @Override
    public void setActionSearchBar() {
        ((MainActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.BOOK_DETAIL);
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
}
