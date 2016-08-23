package com.talentica.presentation.leadCapturePage.notifications;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;
import com.talentica.presentation.utils.NotificationViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class NotificationsRecyclerAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private final String Tag = "NotifiRecyAdapter";
    private List<NotificationModel> notificationModelList;
    private AppCompatActivity appCompatActivity;

    @Inject
    public NotificationsRecyclerAdapter(AppCompatActivity activity) {
        this.notificationModelList = new ArrayList<NotificationModel>();
        appCompatActivity = activity;
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(Tag, "onCreateViewHolder ");
        return NotificationViewHolder.create(LayoutInflater.from(parent.getContext()), parent, appCompatActivity);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bindTo(notificationModelList.get(position));
    }

    private void validateUsersCollection(Collection<NotificationModel> notificationCollection) {
        if (notificationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setNotificationCollection(Collection<NotificationModel> notificationCollection) {
        validateUsersCollection(notificationCollection);
        notificationModelList.addAll(notificationCollection);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != notificationModelList ? notificationModelList.size() : 0);
    }
}
