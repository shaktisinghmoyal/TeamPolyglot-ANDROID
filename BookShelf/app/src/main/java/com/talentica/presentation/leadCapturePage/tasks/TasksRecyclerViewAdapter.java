package com.talentica.presentation.leadCapturePage.tasks;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;
import com.talentica.presentation.utils.RequestedBookViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<RequestedBookViewHolder> {

    private final String Tag = "HomeRecyclerViewAdapter";
    private List<BooksRequestedToUserModel> bookRequestModelList;
    private OnItemClickListener onItemClickListener;
    private AppCompatActivity appCompatActivity;

    @Inject
    public TasksRecyclerViewAdapter(AppCompatActivity activity) {
        this.bookRequestModelList = new ArrayList<BooksRequestedToUserModel>();
        appCompatActivity = activity;
    }

    @Override
    public RequestedBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(Tag, "onCreateViewHolder ");
        return RequestedBookViewHolder.create(LayoutInflater.from(parent.getContext()), parent, appCompatActivity);
    }

    @Override
    public void onBindViewHolder(RequestedBookViewHolder holder, final int position) {
        holder.bindTo(bookRequestModelList.get(position));
        holder.itemView.findViewById(R.id.accept_requested_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onAcceptedClicked(bookRequestModelList.get(position), position);
                }
            }
        });

        holder.itemView.findViewById(R.id.cancel_requested_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onRejectedClicked(bookRequestModelList.get(position), position);

                }
            }
        });


    }

    public void setUsersCollection(Collection<BooksRequestedToUserModel> bookRequestCollection) {
        validateUsersCollection(bookRequestCollection);
        bookRequestModelList.addAll(bookRequestCollection);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        bookRequestModelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, bookRequestModelList.size());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<BooksRequestedToUserModel> bookRequestCollection) {
        if (bookRequestCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getItemCount() {
        return (null != bookRequestModelList ? bookRequestModelList.size() : 0);
    }

    public interface OnItemClickListener {
        void onAcceptedClicked(BooksRequestedToUserModel bookRequestModel, int itemPosition);

        void onRejectedClicked(BooksRequestedToUserModel bookRequestModel, int itemPosition);

    }
}
