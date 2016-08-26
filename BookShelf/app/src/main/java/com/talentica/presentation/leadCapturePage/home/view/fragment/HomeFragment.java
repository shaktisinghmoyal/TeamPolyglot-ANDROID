package com.talentica.presentation.leadCapturePage.home.view.fragment;

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
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.HomePagePresenter;
import com.talentica.presentation.leadCapturePage.home.view.HomeView;
import com.talentica.presentation.leadCapturePage.home.view.adapter.HomeFragmentRecyclerViewAdapter;
import com.talentica.presentation.utils.ClickListenerInterface;
import com.talentica.presentation.utils.DividerItemDecoration;
import com.talentica.presentation.utils.Enums;
import com.talentica.presentation.utils.Util;

import java.util.Collection;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {


    private final String Tag = "HomeFragment";
    private final int RECENTLY_ADDED_BOOKS = 1;
    private final int MOST_READ_BOOKS = 2;
    @Inject
    HomePagePresenter homePagePresenter;
    @Inject
    HomeFragmentRecyclerViewAdapter recentlyAddedBooksRecylerAdapter;
    @Inject
    HomeFragmentRecyclerViewAdapter mostReadBooksRecylerAdapter;
    RecyclerView recentlyAddedBooksRecylerView;
    RecyclerView mostReadBooksReyclerView;
    private HomeFragmentBinding homeFragmentBinding;
    private ClickListenerInterface bookListListener;

    private RecyclerView.OnScrollListener onScrollListener1 = getScrollListener();
    private RecyclerView.OnScrollListener onScrollListener2 = getScrollListener();
    /**
     * Interface for listening user list events.
     */

    private HomeFragmentRecyclerViewAdapter.OnItemClickListener onItemClickListener =
            new HomeFragmentRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onBookItemClicked(BookModel bookModel) {
                    if (HomeFragment.this.homePagePresenter != null && bookModel != null) {
                        HomeFragment.this.homePagePresenter.onBookClick(bookModel);
                    }
                }
            };

    private RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            int lastVisibleItem, visibleItemCount, totalItemCount;
            private int previousTotal = 0; // The total number of items in the dataset after the last load
            private boolean loading = true; // True if we are still waiting for the last set of data to load.
            private int visibleThreshold = 0; // The minimum amount of items to have below your current scroll position before loading more.
            private LinearLayoutManager mLinearLayoutManager;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e(Tag, "onScrolled ");
                super.onScrolled(recyclerView, dx, dy);
                mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //   visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if (!loading && (totalItemCount <= (lastVisibleItem + 1 + visibleThreshold))) {
                    // End has been reached
                    // Do something
                    if (recyclerView.getId() == R.id.recycler_view_recently_added_list) {
                        Log.e(Tag, "loadNextBooksList for recently added " + "called");
                        homePagePresenter.loadNextBooksList(RECENTLY_ADDED_BOOKS);

                    } else {
                        Log.e(Tag, "loadNextBooksList for most read " + "called");
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
        if (context instanceof ClickListenerInterface) {
            this.bookListListener = (ClickListenerInterface) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        final View fragmentView = homeFragmentBinding.getRoot();
        //homeFragmentBinding.setMarsdata(this);
        setupRecyclerViews();

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homePagePresenter.setView(this);

        if (savedInstanceState == null) {
            initializeItems();
        }

    }

    @Override
    public void setBottomBarIconForHome() {
        ((MainActivity) getActivity()).setBottomBarIconForHome();
    }

    @Override
    public void setActionSearchBar() {
        ((MainActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.HOME, getResources().getString(R.string.home_title));
    }

    private void initializeItems() {
        homePagePresenter.initialize();
    }

    @Override
    public void displayRecentlyAddedBooks(Collection<BookModel> books) {
        Log.e(Tag, "displayRecentlyAddedBooks " + "called");
        if (books != null) {
            recentlyAddedBooksRecylerAdapter.setUsersCollection(books);

        }
    }

    @Override
    public void displayMostReadBooks(Collection<BookModel> books) {
        Log.e(Tag, "displayMostReadBooks " + "called");
        if (books != null) {
            mostReadBooksRecylerAdapter.setUsersCollection(books);
        }
    }

    @Override
    public void moveToBooksGridView(int fragmentTitleId) {

        Bundle fragmentTitleBundle = new Bundle();
        fragmentTitleBundle.putInt(Util.fragmentTitle, fragmentTitleId);
        ((MainActivity) getActivity()).navigator.startListAllActivity((MainActivity) getActivity(), fragmentTitleBundle);
    }

    @Override
    public void viewBook(BookModel bookModel) {
        if (this.bookListListener != null) {
            this.bookListListener.onRecyclerViewBookClicked(bookModel);
        }
    }

    @Override
    public void showLoadingRecycler1() {
        homeFragmentBinding.recyclerView1ProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingRecycler1() {
        homeFragmentBinding.recyclerView1ProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetryRecycler1() {
        homeFragmentBinding.viewRetryRecycler1.setVisibility(View.VISIBLE);
        homeFragmentBinding.emptyViewTextRecycler1.setText(R.string.try_again);
    }

    @Override
    public void hideRetryRecycler1() {
        homeFragmentBinding.viewRetryRecycler1.setVisibility(View.GONE);
    }

    @Override
    public void showErrorRecycler1(String message) {
        homeFragmentBinding.errorTextViewRecycler1.setText(R.string.not_connected);
    }

    @Override
    public void disableErrorRecycler1() {

    }

    @Override
    public void showLoadingRecycler2() {
        homeFragmentBinding.recyclerView2ProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingRecycler2() {
        homeFragmentBinding.recyclerView2ProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetryRecycler2() {
        homeFragmentBinding.viewRetryRecycler2.setVisibility(View.VISIBLE);
        homeFragmentBinding.emptyViewTextRecycler2.setText(R.string.try_again);
    }

    @Override
    public void hideRetryRecycler2() {
        homeFragmentBinding.viewRetryRecycler2.setVisibility(View.GONE);
    }

    @Override
    public void showErrorRecycler2(String message) {
        homeFragmentBinding.errorTextViewRecycler2.setText(R.string.not_connected);
    }

    @Override
    public void disableErrorRecycler2() {

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
        bookListListener = null;
    }


    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setupRecyclerViews() {

        recentlyAddedBooksRecylerAdapter.setOnItemClickListener(onItemClickListener);
        mostReadBooksRecylerAdapter.setOnItemClickListener(onItemClickListener);
        homeFragmentBinding.mostReadViewAllText.setOnClickListener(this);
        homeFragmentBinding.recentlyAddedViewAllText.setOnClickListener(this);
        recentlyAddedBooksRecylerView = homeFragmentBinding.recyclerViewRecentlyAddedList;
        mostReadBooksReyclerView = homeFragmentBinding.recyclerViewMostReadList;
        recentlyAddedBooksRecylerView.addOnScrollListener(onScrollListener1);
        mostReadBooksReyclerView.addOnScrollListener(onScrollListener2);

        recentlyAddedBooksRecylerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mostReadBooksReyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recentlyAddedBooksRecylerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.recycler_item_divider, Util.RecyclerViewOrientationHor));
        mostReadBooksReyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.recycler_item_divider, Util.RecyclerViewOrientationHor));

        recentlyAddedBooksRecylerView.setAdapter(recentlyAddedBooksRecylerAdapter);
        mostReadBooksReyclerView.setAdapter(mostReadBooksRecylerAdapter);

        homeFragmentBinding.viewRetryRecycler1.setOnClickListener(this);
        homeFragmentBinding.viewRetryRecycler2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.e(Tag, "onClick");
        switch (v.getId()) {


            case R.id.recently_added_view_all_text:
                homePagePresenter.recentlyAddedViewAllClicked();
                break;

            case R.id.most_read_view_all_text:
                homePagePresenter.mostReadViewAllClicked();
                break;

            case R.id.view_retry_recycler1:
                homePagePresenter.tryAgain(RECENTLY_ADDED_BOOKS);
                break;

            case R.id.view_retry_recycler2:
                homePagePresenter.tryAgain(MOST_READ_BOOKS);
                break;

        }
    }

}
