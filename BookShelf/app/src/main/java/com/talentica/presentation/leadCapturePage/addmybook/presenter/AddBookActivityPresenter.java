package com.talentica.presentation.leadCapturePage.addmybook.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.addmybook.view.AddMyBookActivityView;

import javax.inject.Inject;

public class AddBookActivityPresenter implements IAddBookActivityPresenter {
    private final String Tag = "AddBookPresenter";
    private AddMyBookActivityView addMyBookActivityView;

    @Inject
    public AddBookActivityPresenter() {
        Log.e(Tag, "AddBookPresenter");
    }

    public void setView(@NonNull AddMyBookActivityView view) {
        addMyBookActivityView = view;
    }


    public void initialize(Bundle savedInstanceState) {

        addMyBookActivityView.setActionBar();
        addMyBookActivityView.setFirstFragment(savedInstanceState);

    }

    @Override
    public void setDiscriptionBar(int discriptionId, int stepId) {
        addMyBookActivityView.setDiscriptionBar(discriptionId, stepId);
    }


}
