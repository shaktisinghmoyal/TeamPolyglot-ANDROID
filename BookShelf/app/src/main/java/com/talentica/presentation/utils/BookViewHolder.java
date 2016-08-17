package com.talentica.presentation.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.talentica.databinding.BookThumbnailCardviewItemBinding;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private static final String Tag = "BookViewHolder";
    private BookThumbnailCardviewItemBinding itemBinding;

    private BookViewHolder(BookThumbnailCardviewItemBinding binding, AppCompatActivity activity) {
        super(binding.getRoot());
        binding.getRoot().setLayoutParams(new LinearLayout.LayoutParams(Util.getDeviceWidth(activity) / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        itemBinding = binding;
    }

    public static BookViewHolder create(LayoutInflater inflater, ViewGroup parent, AppCompatActivity activity) {
        Log.e(Tag, "BookViewHolder create ");
        BookThumbnailCardviewItemBinding binding = BookThumbnailCardviewItemBinding.inflate(inflater, parent, false);
        return new BookViewHolder(binding, activity);
    }


    public void bindTo(BookModel user) {


        itemBinding.setBookModel(user);
        itemBinding.executePendingBindings();
    }
}