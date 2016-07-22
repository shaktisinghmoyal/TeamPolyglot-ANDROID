package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.storage.SharedPreferencesStorage;
import com.talentica.domain.repository.ISearchSuggestionRepository;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SearchSuggestionRepository implements ISearchSuggestionRepository {

    private final String Tag = "SearchSuggRepository";
    private DummyRestApi dri;
    private SharedPreferencesStorage sharedPreferencesStorage;

    @Inject
    public SearchSuggestionRepository(DummyRestApi dri, SharedPreferencesStorage sharedPreferencesStorage) {
        this.dri = dri;
        this.sharedPreferencesStorage = sharedPreferencesStorage;
    }


    @Override
    public Observable<String[]> getTopSearches() {
        return dri.dummyTopSearches().map(new Func1<JSONObject, String[]>() {
            @Override
            public String[] call(JSONObject jsonObject) {
                Log.e(Tag, "dummyTopSearches map call");
                //yaha receiver ka transform hota h
                String[] recentSearchArray = {"Adventure", "Biography", "Robin Sharma", "Charles Dickens", "Wing Of Fire", "Jungle Book"};


                return recentSearchArray;
            }
        });
    }

    @Override
    public Observable<String[]> getRecentSearches() {
        return Observable.just(sharedPreferencesStorage.getRecentSearchStrings());
    }
}
