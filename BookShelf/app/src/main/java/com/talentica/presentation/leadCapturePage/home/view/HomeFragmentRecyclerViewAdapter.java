package com.talentica.presentation.leadCapturePage.home.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.BookViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private List<BookModel> bookModelList;


    @Inject
    public HomeFragmentRecyclerViewAdapter() {
        this.bookModelList = new ArrayList<BookModel>();

    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return BookViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup);

    }


    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int position) {
        bookViewHolder.bindTo(bookModelList.get(position));

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

    private void validateUsersCollection(Collection<BookModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getItemCount() {
        return (null != bookModelList ? bookModelList.size() : 0);
    }


}