package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.repository.ISignUpRepository;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SignUpRepository implements ISignUpRepository {
    @Inject
    public SignUpRepository() {

    }

    @Override
    public Observable<String> tryForSignUp() {
        Log.e("SignUpRepository", "tryForSignUp");
        DummyRestApi dri = new DummyRestApi();
        return dri.dummyLoginModule().map(new Func1<JSONObject, String>() {
            @Override
            public String call(JSONObject jsonObject) {
                Log.e("HomeRepository", "call");
                //yaha receiver ka transform hota h
                return "tryForSignUp";
            }
        });
    }
}
