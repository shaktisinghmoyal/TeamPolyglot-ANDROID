package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.repository.IResetPassRepository;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class ResetPassRepository implements IResetPassRepository {

    @Inject
    public ResetPassRepository() {

    }

    @Override
    public Observable<String> tryForResetPass() {
        Log.e("ResetPassRepository", "tryForResetPass");
        DummyRestApi dri = new DummyRestApi();
        return dri.dummyLoginModule().map(new Func1<JSONObject, String>() {
            @Override
            public String call(JSONObject jsonObject) {
                Log.e("HomeRepository", "call");
                //yaha receiver ka transform hota h
                return "tryForResetPass";
            }
        });
    }
}
