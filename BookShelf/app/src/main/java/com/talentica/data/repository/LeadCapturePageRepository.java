package com.talentica.data.repository;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.storage.SharedPreferencesStorage;
import com.talentica.domain.repository.ILeadCapturePageRepository;

import javax.inject.Inject;

import rx.Observable;

public class LeadCapturePageRepository implements ILeadCapturePageRepository {
    private final String Tag = "LeadCapturePageRepository";
    private SharedPreferencesStorage sharedPreferencesStorage;
    private DummyRestApi dri;

    @Inject
    public LeadCapturePageRepository(SharedPreferencesStorage sharedPreferencesStorage, DummyRestApi dri) {
        this.sharedPreferencesStorage = sharedPreferencesStorage;
        this.dri = dri;
    }

    @Override
    public void saveRecentSearch(String recentSearch) {
        sharedPreferencesStorage.saveRecentSearchStrings(recentSearch);
    }

    @Override
    public Observable<Boolean> requestToBorrowBook(int bookId) {
        return dri.borrowRequest();
    }
}
