package com.talentica.presentation.leadCapturePage.base.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.talentica.R;
import com.talentica.databinding.BookThumbnailCardviewItemBinding;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BooksGridViewAdapter extends BaseAdapter {
    private final String Tag = "BookGridViewAdapter";
    private List<BookModel> bookSearchResult = new ArrayList<BookModel>();
    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener onLongItemClickListener;
    private AppCompatActivity appCompatActivity;

    public BooksGridViewAdapter(AppCompatActivity activity) {
        appCompatActivity = activity;
    }

    @Override
    public int getCount() {
        return bookSearchResult.size();
    }

    @Override
    public BookModel getItem(int position) {
        return bookSearchResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final BookThumbnailCardviewItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.book_thumbnail_cardview_item, parent, false);
            convertView = binding.getRoot();
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (Util.getDeviceHeight(appCompatActivity)) / 3));
            convertView.setTag(binding);

        } else {
            binding = (BookThumbnailCardviewItemBinding) convertView.getTag();
        }

        binding.setBookModel(getItem(position));
        binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongItemClickListener != null) {
                    //   Log.e(Tag,"onBindViewHolder onItemClickListener != null");
                    onLongItemClickListener.onBookItemLongClicked(binding.bookThumbnailImage, binding.bookThumbnailSelect, position, bookSearchResult.get(position));
                }
                return false;
            }
        });
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Log.e(Tag,"onBindViewHolder onClick");
                if (onItemClickListener != null) {
                    //   Log.e(Tag,"onBindViewHolder onItemClickListener != null");
                    onItemClickListener.onBookItemClicked(binding.bookThumbnailImage, binding.bookThumbnailSelect, position, bookSearchResult.get(position));
                }
            }
        });
        return convertView;
    }

    public void addBookSearchResult(Collection<BookModel> searchResults) {
        Log.e(Tag, "books searchResults" + searchResults);
        bookSearchResult.addAll(searchResults);
        notifyDataSetChanged();

    }

    public void clearData() {
        Log.e(Tag, " clearData");
        bookSearchResult.clear();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public interface OnItemClickListener {
        void onBookItemClicked(ImageView bookImage, ImageView selectImage, int position, BookModel bookModel);
    }

    public interface OnLongItemClickListener {
        void onBookItemLongClicked(ImageView bookImage, ImageView selectImage, int position, BookModel bookModel);
    }
}
