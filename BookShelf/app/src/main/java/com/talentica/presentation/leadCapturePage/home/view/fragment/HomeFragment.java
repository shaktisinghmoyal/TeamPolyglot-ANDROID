package com.talentica.presentation.leadCapturePage.home.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.HomeFragmentBinding;
import com.talentica.presentation.internal.di.components.BookComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.HomePagePresenter;
import com.talentica.presentation.leadCapturePage.home.view.HomeView;
import com.talentica.presentation.leadCapturePage.home.view.adapter.HomeFragmentRecyclerViewAdapter;
import com.talentica.presentation.utils.DividerItemDecoration;

import java.util.Collection;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeView {


    private final int RECENTLY_ADDED_BOOKS = 1;
    private final int MOST_READ_BOOKS = 2;
    @Inject
    HomePagePresenter homePagePresenter;
    @Inject
    HomeFragmentRecyclerViewAdapter recentlyAddedBooksRecylerAdapter;
    @Inject
    HomeFragmentRecyclerViewAdapter mostReadBooksReyclerAdapter;
    RecyclerView recentlyAddedBooksRecylerView;
    RecyclerView mostReadBooksReyclerView;
    private HomeFragmentBinding homeFragmentBinding;
    private BookListListener bookListListener;

    private RecyclerView.OnScrollListener onScrollListener1 = getScrollListener();
    private RecyclerView.OnScrollListener onScrollListener2 = getScrollListener();

    private RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            int firstVisibleItem, visibleItemCount, totalItemCount;
            private int previousTotal = 0; // The total number of items in the dataset after the last load
            private boolean loading = true; // True if we are still waiting for the last set of data to load.
            private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
            private LinearLayoutManager mLinearLayoutManager;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

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
                    if (recyclerView.getId() == R.id.recycler_view_recently_added_list) {
                        homePagePresenter.loadNextBooksList(RECENTLY_ADDED_BOOKS);
                    } else {
                        homePagePresenter.loadNextBooksList(MOST_READ_BOOKS);
                    }

                    loading = true;
                }

            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.bookListListener = (BookListListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(BookComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        final View fragmentView = homeFragmentBinding.getRoot();
        //homeFragmentBinding.setMarsdata(this);
        setupRecyclerViews(fragmentView);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homePagePresenter.setView(this);
        if (savedInstanceState == null) {
            initializeBooksList();
        }

    }

    private void initializeBooksList() {
        homePagePresenter.initialize();
    }

    @Override
    public void displayRecentlyAddedBooks(Collection<BookModel> books) {
        Log.e("displaybooks", "called");
        if (books != null) {
            recentlyAddedBooksRecylerAdapter.setUsersCollection(books);

        }
    }

    @Override
    public void displayMostReadBooks(Collection<BookModel> books) {
        if (books != null) {
            mostReadBooksReyclerAdapter.setUsersCollection(books);
        }
    }

    @Override
    public void viewBook(BookModel bookModel) {

    }

    @Override
    public void onResume() {
        super.onResume();
        homePagePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        homePagePresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recentlyAddedBooksRecylerView.setAdapter(null);
        mostReadBooksReyclerView.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePagePresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homePagePresenter = null;
    }

    @Override
    public void showLoading() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.getActivity().setProgressBarIndeterminateVisibility(false);
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
    public Context context() {
        return getActivity().getApplicationContext();
    }


    private void setupRecyclerViews(View view) {


        recentlyAddedBooksRecylerView = homeFragmentBinding.recyclerViewRecentlyAddedList;
        mostReadBooksReyclerView = homeFragmentBinding.recyclerViewMostReadList;
        recentlyAddedBooksRecylerView.addOnScrollListener(onScrollListener1);
        mostReadBooksReyclerView.addOnScrollListener(onScrollListener2);

        recentlyAddedBooksRecylerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mostReadBooksReyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recentlyAddedBooksRecylerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.recycler_item_divider));
        mostReadBooksReyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.recycler_item_divider));

        recentlyAddedBooksRecylerView.setAdapter(recentlyAddedBooksRecylerAdapter);
        mostReadBooksReyclerView.setAdapter(mostReadBooksReyclerAdapter);

    }

    /**
     * Interface for listening user list events.
     */
    public interface BookListListener {
        void onBookClicked(final BookModel book);
    }

}
