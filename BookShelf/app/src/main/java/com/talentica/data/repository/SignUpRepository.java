package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.repository.ISignUpRepository;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SignUpRepository implements ISignUpRepository {
    private DummyRestApi dri;

    @Inject
    public SignUpRepository(DummyRestApi dri) {
        this.dri = dri;
    }

    @Override
    public Observable<String> tryForSignUp(String username, String password, String fullName) {
//        Log.e("SignUpRepository", "tryForSignUp");
        return dri.dummyLoginModule(username, password).map(new Func1<JSONObject, String>() {
            @Override
            public String call(JSONObject jsonObject) {
                Log.e("SignUpRepository", "call");
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
