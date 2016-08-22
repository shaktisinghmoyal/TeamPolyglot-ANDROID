package com.talentica.presentation.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talentica.databinding.SingleTaskLayoutBinding;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;

public class RequestedBookViewHolder extends RecyclerView.ViewHolder {
    private static final String Tag = "RequestedBookViewHolder";
    private SingleTaskLayoutBinding binding;

    private RequestedBookViewHolder(SingleTaskLayoutBinding binding, AppCompatActivity activity) {
        super(binding.getRoot());
        // binding.getRoot().setLayoutParams(new LinearLayout.LayoutParams(Util.getDeviceWidth(activity) / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.binding = binding;
    }

    public static RequestedBookViewHolder create(LayoutInflater inflater, ViewGroup parent, AppCompatActivity activity) {
        Log.e(Tag, "BookViewHolder create ");
        SingleTaskLayoutBinding binding = SingleTaskLayoutBinding.inflate(inflater, parent, false);
        return new RequestedBookViewHolder(binding, activity);
    }

    public void bindTo(BooksRequestedToUserModel booksRequestedToUserModel) {
        binding.setBookRequestModel(booksRequestedToUserModel);
        binding.executePendingBindings();
    }
}
