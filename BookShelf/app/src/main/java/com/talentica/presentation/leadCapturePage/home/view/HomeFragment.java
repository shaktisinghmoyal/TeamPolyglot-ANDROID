package com.talentica.presentation.leadCapturePage.home.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.presentation.internal.di.components.BookComponent;
import com.talentica.presentation.leadCapturePage.base.view.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.presenter.HomePagePresenter;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeView {


    @Inject
    HomePagePresenter homePagePresenter;
    @Inject
    HomeFragmentRecyclerViewAdapter recyclerViewAdapter1;
    @Inject
    HomeFragmentRecyclerViewAdapter recyclerViewAdapter2;
    RecyclerView recycler_view1;
    RecyclerView recycler_view2;
    ArrayList<BookModel> mostReadBooks;
    ArrayList<BookModel> recentlyAddedReadBooks;
    private BookListListener bookListListener;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
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
                loadBookList();

                loading = true;
            }

        }
    };

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
        final View fragmentView = inflater.inflate(R.layout.home_fragment, container, false);
        setupRecyclerViews(fragmentView);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePagePresenter.setView(this);
        if (savedInstanceState == null) {
            loadBookList();
        }

    }

    private void loadBookList() {
        homePagePresenter.initialize();
    }

    @Override
    public void displayRecentlyAddedBooks(Collection<BookModel> books) {
        Log.e("displaybooks", "called");
        if (books != null) {
            this.recyclerViewAdapter1.setUsersCollection(books);
            // this.recyclerViewAdapter2.setUsersCollection(books);
        }
    }

    @Override
    public void displayMostReadBooks(Collection<BookModel> books) {
        if (books != null) {
            this.recyclerViewAdapter2.setUsersCollection(books);
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
        recycler_view1.setAdapter(null);
        recycler_view2.setAdapter(null);

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

        mostReadBooks = new ArrayList<BookModel>();
        recentlyAddedReadBooks = new ArrayList<BookModel>();

        for (int j = 0; j <= 5; j++) {
            mostReadBooks.add(new BookModel(" Mahabharta", " Arvind", " Shakti "));
            recentlyAddedReadBooks.add(new BookModel("Ramayana", " Tomy ", " Akash "));
        }

        recycler_view1 = (RecyclerView) view.findViewById(R.id.recycler_view_recently_added_list);
        recycler_view2 = (RecyclerView) view.findViewById(R.id.recycler_view_most_list);
        recycler_view1.addOnScrollListener(onScrollListener);

        recycler_view1.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_view2.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recycler_view1.setAdapter(recyclerViewAdapter1);
        recycler_view2.setAdapter(recyclerViewAdapter2);


        recyclerViewAdapter1.setUsersCollection(recentlyAddedReadBooks);
        recyclerViewAdapter2.setUsersCollection(mostReadBooks);

    }

    /**
     * Interface for listening user list events.
     */
    public interface BookListListener {
        void onBookClicked(final BookModel book);
    }

}
