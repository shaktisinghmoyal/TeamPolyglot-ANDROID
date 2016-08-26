package com.talentica.presentation.leadCapturePage.notifications.view.fragment;


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
import com.talentica.databinding.NotificationFragmentBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.notifications.NotificationsRecyclerAdapter;
import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;
import com.talentica.presentation.leadCapturePage.notifications.presenter.NotificationPresenterImpl;
import com.talentica.presentation.leadCapturePage.notifications.view.NotificationView;
import com.talentica.presentation.utils.DividerItemDecoration;
import com.talentica.presentation.utils.Enums;
import com.talentica.presentation.utils.Util;

import java.util.Collection;

import javax.inject.Inject;

public class NotificationFragment extends BaseFragment implements NotificationView {
    private final String Tag = "NotificationFragment";

    @Inject
    NotificationPresenterImpl presenter;
    @Inject
    NotificationsRecyclerAdapter adapter;

    NotificationFragmentBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.OnScrollListener onScrollListener1 = getScrollListener();

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
                //  visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                Log.e(Tag, "totalItemCount " + totalItemCount);
                Log.e(Tag, "lastVisibleItem " + lastVisibleItem);
                Log.e(Tag, "visibleThreshold " + visibleThreshold);
                Log.e(Tag, "loading == " + loading);
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount <= (lastVisibleItem + 1 + visibleThreshold))) {
                    // End has been reached
                    // Do something
                    Log.e(Tag, "totalItemCount " + totalItemCount);
                    Log.e(Tag, "lastVisibleItem " + lastVisibleItem);
                    Log.e(Tag, "visibleThreshold " + visibleThreshold);

                    presenter.loadNotifications();

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
        binding = DataBindingUtil.inflate(inflater, R.layout.notification_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(Tag, "onViewCreated " + this);
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        presenter.setView(this);
        presenter.initialize();
    }

    private void setupRecyclerView() {
        recyclerView = binding.notificationList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.recycler_item_divider, !Util.RecyclerViewOrientationHor));
        recyclerView.addOnScrollListener(onScrollListener1);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void renderNotificationView(Collection<NotificationModel> notificationModels) {
        Log.e(Tag, "renderMyTasks ");
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setNotificationCollection(notificationModels);
    }

    @Override
    public void refreshNotifications() {

    }

    @Override
    public void setActionBar() {
        ((MainActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.NOTIFICATION, getResources().getString(R.string.notifications));
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter = null;

    }

    @Override
    public void showLoading() {
        binding.notificationListProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.notificationListProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        binding.viewRetryNotificationList.setVisibility(View.VISIBLE);
        binding.emptyViewTextNotificationList.setText(R.string.try_again);
    }

    @Override
    public void hideRetry() {
        binding.viewRetryNotificationList.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        binding.errorTextViewNotificationList.setText(R.string.not_connected);
    }

    @Override
    public void disableError() {

    }

    @Override
    public Context context() {

        return getActivity().getApplicationContext();
    }
}
