package com.talentica.presentation.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.talentica.databinding.BookThumbnailCardviewItemBinding;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private BookThumbnailCardviewItemBinding itemBinding;

    private BookViewHolder(BookThumbnailCardviewItemBinding binding) {
        super(binding.getRoot());
        binding.getRoot().setLayoutParams(new LinearLayout.LayoutParams(Util.getDeviceWidth() / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        itemBinding = binding;
    }

    public static BookViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        BookThumbnailCardviewItemBinding binding = BookThumbnailCardviewItemBinding.inflate(inflater, parent, false);

        return new BookViewHolder(binding);
    }


    public void bindTo(BookModel user) {
        itemBinding.setBookModel(user);
        itemBinding.executePendingBindings();
    }
}