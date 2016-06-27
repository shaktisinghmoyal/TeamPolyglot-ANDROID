package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.RestApiImpl;
import com.talentica.domain.repository.ISignInRepository;

import javax.inject.Inject;

import rx.Observable;

public class SignInRepository implements ISignInRepository {
    @Inject
    RestApiImpl restApi;


    @Inject
    public SignInRepository() {

    }

    @Override
    public Observable<String> tryForSignIn(String username, String password) {
        Log.e("SignInRepository", "tryForSignIn " + username + " " + password);

        return restApi.callSignInApi(username, password);


//        DummyRestApi dri = new DummyRestApi();
//        return dri.dummyLoginModule().map(new Func1<JSONObject, String>() {
//            @Override
//            public String call(JSONObject jsonObject) {
//                Log.e("HomeRepository", "call");
//                //yaha receiver ka transform hota h
//                return "SignInHoGaya";
//            }
//        });
    }
}
