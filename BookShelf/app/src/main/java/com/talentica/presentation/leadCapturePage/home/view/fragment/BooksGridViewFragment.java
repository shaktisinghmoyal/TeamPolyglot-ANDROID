package com.talentica.presentation.leadCapturePage.home.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.talentica.R;
import com.talentica.databinding.BooksGridViewLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.BooksGridViewPresenter;
import com.talentica.presentation.leadCapturePage.home.view.BooksGridView;
import com.talentica.presentation.leadCapturePage.home.view.adapter.BookResultGridViewAdapter;
import com.talentica.presentation.utils.ClickListenerInterface;

import java.util.Collection;

import javax.inject.Inject;

public class BooksGridViewFragment extends BaseFragment implements BooksGridView {
    private final String Tag = "BooksGridViewFragment";
    @Inject
    BooksGridViewPresenter booksGridViewPresenter;
    private ClickListenerInterface bookGridViewListener;
    private BooksGridViewLayoutBinding booksGridViewLayoutBinding;
    private BookResultGridViewAdapter gridViewAdapter;
    private GridView gridView;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int fragmentTitleId;

    private GridView.OnScrollListener onGridViewScrollListener = getGridViewScrollListener();
    private BookResultGridViewAdapter.OnItemClickListener onItemClickListener =
            new BookResultGridViewAdapter.OnItemClickListener() {
                @Override
                public void onBookItemClicked(BookModel bookModel) {
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
                    booksGridViewPresenter.loadBookSearchResults();
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

        if (getArguments() != null) {
            fragmentTitleId = getArguments().getInt(MainActivity.fragmentTitle);
            Log.e(Tag, "GridViewFragment bundle" + " " + savedInstanceState);
        }
        initializeBookGridViewResult();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClickListenerInterface) {
            this.bookGridViewListener = (ClickListenerInterface) context;
        }
    }

    private void initializeBookGridViewResult() {
        booksGridViewPresenter.initialize();
    }

    @Override
    public void showSearchBookResults(Collection<BookModel> books) {
        Log.e(Tag, "showSearchBookResults " + "books " + books);

        gridViewAdapter.addBookSearchResult(books);
    }

    @Override
    public void viewBook(BookModel bookModel) {
        if (this.bookGridViewListener != null) {
            this.bookGridViewListener.onGridViewBookClicked(bookModel);
        }
    }

    @Override
    public void setFragmentTitle() {
        booksGridViewLayoutBinding.fragmentTitle.setText(getResources().getString(fragmentTitleId));
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
        bookGridViewListener = null;
    }

    @Override
    public void showLoading() {
        // getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        //  this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void disableError() {

    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void setActionSearchBar() {

    }

    private void setupGridView() {

        gridView = booksGridViewLayoutBinding.bookGridView;
        gridView.setOnScrollListener(onGridViewScrollListener);
        gridViewAdapter = new BookResultGridViewAdapter();
        gridViewAdapter.setOnItemClickListener(onItemClickListener);
        gridView.setAdapter(gridViewAdapter);

    }
}
