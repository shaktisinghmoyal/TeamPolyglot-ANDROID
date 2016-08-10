package com.talentica.presentation.leadCapturePage.home.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.base.presenter.Presenter;
import com.talentica.presentation.leadCapturePage.home.view.ListAllView;

import javax.inject.Inject;

public class ListAllActivityPresenter implements IListAllActivityPresenter, Presenter {
    private final String Tag = "ListAllPresenter";
    private ListAllView listAllView;

    @Inject
    public ListAllActivityPresenter() {
        Log.e(Tag, "ListAllActivityPresenter");


    }


    public void setView(@NonNull ListAllView view) {
        listAllView = view;
    }


    public void initialize() {
        renderBooksGridView();
    }


    @Override
    public void renderBooksGridView() {
        listAllView.addGridViewFragment();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.listAllView = null;
    }

//
}
