package com.talentica.presentation.leadCapturePage.tasks.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.tasks.view.MyTaskActivityView;

import javax.inject.Inject;

public class MyTaskActivityPresenter implements IMyTaskActivityPresenter {
    private final String Tag = "TaskActivityPresenter";
    private MyTaskActivityView myTaskActivityView;

    @Inject
    public MyTaskActivityPresenter() {
        Log.e(Tag, "TaskActivityPresenter");
    }

    public void setView(@NonNull MyTaskActivityView view) {
        myTaskActivityView = view;
    }

    @Override
    public void updateActionBar(int textId, String taskCount) {
        myTaskActivityView.updateActionBar(textId, taskCount);
    }

    public void initialize() {
        myTaskActivityView.setFirstFragment();

    }
}
