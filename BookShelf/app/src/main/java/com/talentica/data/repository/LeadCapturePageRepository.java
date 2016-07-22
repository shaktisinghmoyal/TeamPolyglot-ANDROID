package com.talentica.data.repository;

import com.talentica.data.storage.SharedPreferencesStorage;
import com.talentica.domain.repository.ILeadCapturePageRepository;

import javax.inject.Inject;

public class LeadCapturePageRepository implements ILeadCapturePageRepository {
    private final String Tag = "LeadCapturePageRepository";
    private SharedPreferencesStorage sharedPreferencesStorage;

    @Inject
    public LeadCapturePageRepository(SharedPreferencesStorage sharedPreferencesStorage) {
        this.sharedPreferencesStorage = sharedPreferencesStorage;
    }

    @Override
    public void saveRecentSearch(String recentSearch) {
        sharedPreferencesStorage.saveRecentSearchStrings(recentSearch);
    }
}
