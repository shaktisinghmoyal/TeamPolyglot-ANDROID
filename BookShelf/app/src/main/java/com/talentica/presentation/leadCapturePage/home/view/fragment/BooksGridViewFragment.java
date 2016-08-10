package com.talentica.presentation.leadCapturePage.home.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;

import com.talentica.R;
import com.talentica.databinding.BooksGridViewLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.BooksGridViewPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BooksGridView;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.ListAllActivity;
import com.talentica.presentation.leadCapturePage.home.view.adapter.BookResultGridViewAdapter;
import com.talentica.presentation.utils.Enums;
import com.talentica.presentation.utils.GridViewItemClickListnerInterface;
import com.talentica.presentation.utils.Util;

import java.util.Collection;

import javax.inject.Inject;

public class BooksGridViewFragment extends BaseFragment implements BooksGridView, View.OnClickListener {
    private final String Tag = "BooksGridViewFragment";
    @Inject
    BooksGridViewPresenter booksGridViewPresenter;
    private GridViewItemClickListnerInterface gridViewItemClickListnerInterface;
    private BooksGridViewLayoutBinding booksGridViewLayoutBinding;
    private BookResultGridViewAdapter gridViewAdapter;
    private GridView gridView;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int fragmentTitleId;
    private Enums.gridViewType gridViewType;

    private GridView.OnScrollListener onGridViewScrollListener = getGridViewScrollListener();
    private BookResultGridViewAdapter.OnItemClickListener onItemClickListener =
            new BookResultGridViewAdapter.OnItemClickListener() {
                @Override
                public void onBookItemClicked(ImageView bookImage, ImageView selectImage, int position, BookModel bookModel) {
                    if (booksGridViewPresenter != null && bookModel != null) {
                        booksGridViewPresenter.onGridViewBookClick(bookModel);
                    }
                }
            };

    private GridView.OnScrollListener getGridViewScrollListener() {
        return new GridView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    // Do something
                    booksGridViewPresenter.loadBookSearchResults(gridViewType);
                    //  homePagePresenter.loadNextBooksList(MOST_READ_BOOKS);


                    loading = true;
                }
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        booksGridViewLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.books_grid_view_layout, container, false);

        return booksGridViewLayoutBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(Tag, "onViewCreated " + this);
        super.onViewCreated(view, savedInstanceState);
        setupGridView();
        booksGridViewPresenter.setView(this);
        booksGridViewLayoutBinding.viewRetry.setOnClickListener(this);
        if (getArguments() != null) {
            fragmentTitleId = getArguments().getInt(Util.fragmentTitle);
            Log.e(Tag, "GridViewFragment bundle" + " " + savedInstanceState);

            if (getResources().getString(fragmentTitleId).equals(getResources().getString(R.string.results_string))) {
                gridViewType = Enums.gridViewType.DIRECT_SEARCH;
            } else if (getResources().getString(fragmentTitleId).equals(getResources().getString(R.string.recently_added_text))) {
                gridViewType = Enums.gridViewType.RECENTLY_ADDED;
            } else {
                gridViewType = Enums.gridViewType.MOST_READ;
            }
        }
        initializeBookGridViewResult();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GridViewItemClickListnerInterface) {

            this.gridViewItemClickListnerInterface = (GridViewItemClickListnerInterface) context;
        }
    }

    private void initializeBookGridViewResult() {
        booksGridViewPresenter.initialize(gridViewType);
    }

    @Override
    public void showSearchBookResults(Collection<BookModel> books) {
        Log.e(Tag, "showSearchBookResults " + "books " + books);
        booksGridViewLayoutBinding.mainGridView.setVisibility(View.VISIBLE);
        gridViewAdapter.addBookSearchResult(books);
    }

    @Override
    public void viewBook(BookModel bookModel) {
        if (this.gridViewItemClickListnerInterface != null) {
            Log.e(Tag, "viewBook onGridViewBookClicked " + " ");
            this.gridViewItemClickListnerInterface.onGridViewBookClicked(bookModel);
        }
    }

    @Override
    public void setFragmentTitle() {
        booksGridViewLayoutBinding.fragmentTitle.setText(getResources().getString(fragmentTitleId));
    }


    @Override
    public void setActionBar() {
        if (getResources().getString(fragmentTitleId).equals(getResources().getString(R.string.results_string))) {
            Log.e(Tag, "setActionBar  " + " No action bar required");
        } else if (getResources().getString(fragmentTitleId).equals(getResources().getString(R.string.recently_added_text))) {
            ((ListAllActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A2D35")));
            ((ListAllActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((ListAllActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.recently_added_text));
        } else {
            ((ListAllActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A2D35")));
            ((ListAllActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((ListAllActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.most_read_text));
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        booksGridViewPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        booksGridViewPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gridView.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        booksGridViewPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        booksGridViewPresenter = null;
        gridViewItemClickListnerInterface = null;
    }

    @Override
    public void showLoading() {
        // getActivity().setProgressBarIndeterminateVisibility(true);
        booksGridViewLayoutBinding.gridViewProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        //  this.getActivity().setProgressBarIndeterminateVisibility(false);
        booksGridViewLayoutBinding.gridViewProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        booksGridViewLayoutBinding.viewRetry.setVisibility(View.VISIBLE);
        booksGridViewLayoutBinding.emptyViewText.setText(R.string.try_again);
    }

    @Override
    public void hideRetry() {
        booksGridViewLayoutBinding.viewRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        booksGridViewLayoutBinding.errorTextView.setText(R.string.not_connected);
    }

    @Override
    public void disableError() {

    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }



    private void setupGridView() {

        gridView = booksGridViewLayoutBinding.bookGridView;
        gridView.setOnScrollListener(onGridViewScrollListener);
        gridViewAdapter = new BookResultGridViewAdapter();
        gridViewAdapter.setOnItemClickListener(onItemClickListener);
        gridView.setAdapter(gridViewAdapter);

    }

    @Override
    public void onClick(View v) {
        Log.e(Tag, "onClick  " + v);
        switch (v.getId()) {


            case R.id.view_retry:
                booksGridViewPresenter.retry(gridViewType);

                break;


        }
    }
}
