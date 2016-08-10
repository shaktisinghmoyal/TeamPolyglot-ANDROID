package com.talentica.presentation.leadCapturePage.base.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.leadCapturePage.base.view.LeadCapturePageView;

import javax.inject.Inject;
import javax.inject.Named;

public class LeadCapturePagePresenter implements ILeadCapturePagePresenter, Presenter {
    private final String Tag = "LeadCapturePresenter";
    private final BaseUseCase saveRecentSearches;
    private LeadCapturePageView leadCapturePageView;

    @Inject
    public LeadCapturePagePresenter(@Named("saveRecentSearches") BaseUseCase saveRecentSearches) {
        Log.e(Tag, "LeadCapturePagePresenter");
        this.saveRecentSearches = saveRecentSearches;

    }


    public void setView(@NonNull LeadCapturePageView view) {
        leadCapturePageView = view;
    }


    public void initialize(Bundle savedInstanceState) {
        leadCapturePageView.setFirstFragment(savedInstanceState);
    }


    @Override
    public void navigatePage() {

    }

    @Override
    public void getCurrentPage() {

    }

    @Override
    public void onBottomMenuItemClick() {

    }

    @Override
    public void enableBottomMenu() {
        leadCapturePageView.showBottomMenu();
    }

    @Override
    public void disableBottomMenu() {
        leadCapturePageView.hideBottomMenu();
    }

    @Override
    public void saveForRecentSearches(String recentSearch) {
        saveRecentSearches.execute(recentSearch, new SubscriberToSaveData());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onDrawerItemClicked() {
        leadCapturePageView.displaySearchResultForDrawerItem();
    }

    @Override
    public void destroy() {
        saveRecentSearches.unsubscribe();
        this.leadCapturePageView = null;
    }

    private final class SubscriberToSaveData extends DefaultSubscriber<String> {
        int queryNo;

        public SubscriberToSaveData() {

        }

        @Override
        public void onCompleted() {
//            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
//            hideViewLoading();
//            showErrorMessage(new DefaultErrorBundle((Exception) e));
//            showViewRetry();
        }

        @Override
        public void onNext(String isDataSent) {

//            showBooksCollectionInView(books, queryNo);
        }
    }
}
