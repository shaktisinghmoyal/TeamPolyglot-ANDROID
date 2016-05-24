package com.talentica.presentation.leadCapturePage.home.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.talentica.R;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HomeFragmentRecyclerViewAdapter.SingleItemRowHolder> {

    private List<BookModel> itemsList;
    private Context mContext;

    @Inject
    public HomeFragmentRecyclerViewAdapter(Context context) {
        this.itemsList = new ArrayList<BookModel>();
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_thumbnail_cardview_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        BookModel singleItem = itemsList.get(i);

        holder.bookTitle.setText(singleItem.getBookName());
        holder.bookAuther.setText(singleItem.getAuthersName());
        holder.bookLender.setText(singleItem.getLender());


       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    public void setUsersCollection(Collection<BookModel> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.itemsList.addAll(usersCollection);
        this.notifyDataSetChanged();
    }

    private void validateUsersCollection(Collection<BookModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView bookImage;
        protected TextView bookTitle;
        protected TextView bookAuther;
        protected TextView bookLender;


        public SingleItemRowHolder(View view) {
            super(view);

            this.bookImage = (ImageView) view.findViewById(R.id.book_thumbnail_image);
            this.bookTitle = (TextView) view.findViewById(R.id.book_name);
            this.bookAuther = (TextView) view.findViewById(R.id.book_author);
            this.bookLender = (TextView) view.findViewById(R.id.book_lender);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //   Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }


    }

}