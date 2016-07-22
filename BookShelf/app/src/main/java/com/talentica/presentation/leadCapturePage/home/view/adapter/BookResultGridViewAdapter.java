package com.talentica.presentation.leadCapturePage.home.view.adapter;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.talentica.R;
import com.talentica.databinding.BookThumbnailCardviewItemBinding;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookResultGridViewAdapter extends BaseAdapter {
    private final String Tag = "BookGridViewAdapter";
    private List<BookModel> bookSearchResult = new ArrayList<BookModel>();
    private OnItemClickListener onItemClickListener;

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

        BookThumbnailCardviewItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.book_thumbnail_cardview_item, parent, false);
            convertView = binding.getRoot();
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (Util.getDeviceHeight()) / 3));
            convertView.setTag(binding);

        } else {
            binding = (BookThumbnailCardviewItemBinding) convertView.getTag();
        }
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Log.e(Tag,"onBindViewHolder onClick");
                if (onItemClickListener != null) {
                    //   Log.e(Tag,"onBindViewHolder onItemClickListener != null");
                    onItemClickListener.onBookItemClicked(bookSearchResult.get(position));
                }
            }
        });
        binding.setBookModel(getItem(position));


        return convertView;
    }

    public void addBookSearchResult(Collection<BookModel> searchResults) {
        Log.e(Tag, "books searchResults" + searchResults);

        bookSearchResult.addAll(searchResults);
        notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onBookItemClicked(BookModel bookModel);
    }

}
