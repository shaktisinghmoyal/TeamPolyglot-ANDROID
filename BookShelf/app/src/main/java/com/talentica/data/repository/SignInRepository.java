package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.networking.RestApiImpl;
import com.talentica.domain.repository.ISignInRepository;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SignInRepository implements ISignInRepository {
    @Inject
    RestApiImpl restApi;

    private DummyRestApi dri;


    @Inject
    public SignInRepository(DummyRestApi dri) {
        this.dri = dri;
    }

    @Override
    public Observable<String> tryForSignIn(String username, String password) {
//        Log.e("SignInRepository", "tryForSignIn " + username + " " + password);


        return dri.dummyLoginModule(username, password).map(new Func1<JSONObject, String>() {
            @Override
            public String call(JSONObject jsonObject) {
                Log.e("SignInRepository", "call");
                //yaha receiver ka transform hota h
                String result = "false";
                try {
                    result = jsonObject.getString("result");
                    Log.e("result jsonObject ", result);
                } catch (JSONException j) {

                }
                return result;
            }
        });
    }
}
