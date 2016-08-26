package com.talentica.presentation.leadCapturePage.myaccount.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.BooksBorrowedLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;

public class BorrowedBooksFragment extends BaseFragment {
    private final String Tag = "BorrowedBooksFragment";
    private BooksBorrowedLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.books_borrowed_layout, container, false);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(Tag, "onCreate ");
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

}
