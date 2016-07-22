package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.repository.IResetPassRepository;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class ResetPassRepository implements IResetPassRepository {

    private final String Tag = "ResetPassRepository";
    private DummyRestApi dri;

    @Inject
    public ResetPassRepository(DummyRestApi dri) {
        this.dri = dri;
    }

    @Override
    public Observable<String> tryForResetPass(String email) {
//        Log.e("ResetPassRepository", "tryForResetPass");
        String string1 = "reshakt";
        String string2 = "SHAmoy123";

        if (!email.equals("shakti.singh0708@gmail.com")) {
            string1 = "invalid";
            string2 = "invalid";
        }
        return dri.dummyLoginModule(string1, string2).map(new Func1<JSONObject, String>() {
            @Override
            public String call(JSONObject jsonObject) {
                Log.e(Tag, "call");
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
