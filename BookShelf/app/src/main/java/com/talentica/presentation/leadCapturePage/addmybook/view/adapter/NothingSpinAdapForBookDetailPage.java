package com.talentica.presentation.leadCapturePage.addmybook.view.adapter;

import android.content.Context;
import android.widget.SpinnerAdapter;

import com.talentica.presentation.leadCapturePage.base.view.adapter.NothingSelectedSpinnerAdapter;

public class NothingSpinAdapForBookDetailPage extends NothingSelectedSpinnerAdapter {

    public NothingSpinAdapForBookDetailPage(SpinnerAdapter spinnerAdapter,
                                            int nothingSelectedLayout, Context context) {
        super(spinnerAdapter, nothingSelectedLayout, context);
    }
}
