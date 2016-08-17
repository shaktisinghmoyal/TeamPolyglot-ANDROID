package com.talentica.presentation.leadCapturePage.home.view.adapter;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.BookViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private final String Tag = "HomeRecyclerViewAdapter";
    private List<BookModel> bookModelList;
    private OnItemClickListener onItemClickListener;
    private AppCompatActivity appCompatActivity;
    @Inject
    public HomeFragmentRecyclerViewAdapter(AppCompatActivity activity) {
        this.bookModelList = new ArrayList<BookModel>();
        appCompatActivity = activity;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e(Tag, "onCreateViewHolder " + i);
        return BookViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, appCompatActivity);

    }

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, final int position) {
        //Log.e(Tag,"onBindViewHolder ");
        bookViewHolder.bindTo(bookModelList.get(position));
        bookViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.e(Tag,"onBindViewHolder onClick");
                if (onItemClickListener != null) {
                    Log.e(Tag, "onBindViewHolder onItemClickListener != null");
                    onItemClickListener.onBookItemClicked(bookModelList.get(position));
                }
            }
        });
       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    public void setUsersCollection(Collection<BookModel> usersCollection) {
        validateUsersCollection(usersCollection);
        bookModelList.addAll(usersCollection);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<BookModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getItemCount() {
        return (null != bookModelList ? bookModelList.size() : 0);
    }

    public interface OnItemClickListener {
        void onBookItemClicked(BookModel bookModel);
    }


}