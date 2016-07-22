package com.talentica.data.networking;

import android.util.Log;

import com.android.volley.Request;
import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.authentication.CredentialToJsonMapper;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class RestApiImpl extends BaseClassForMethods implements RestApi, BaseURL {
    private final String Tag = "RestApiImpl";
    @Inject
    public RestApiImpl() {
    }

    @Override
    public Observable<List<BookEntity>> recentlyAddedBookList() {
        return null;
    }

    @Override
    public Observable<List<BookEntity>> mostReadBookList() {
        return null;
    }

    @Override
    public Observable<List<BookEntity>> abstractSearch(String stringForSearch) {
        return null;
    }

    @Override
    public Observable<String> callSignInApi(String userName, String passWord) {
        Log.d(Tag, "callSignInApi called ");

        Observable<JSONObject> objectObservable;
        Observable<String> observable;
        try {
            objectObservable = CallNetworkingLibrary.callApiRequest(API_LOGIN, Request.Method.POST, CredentialToJsonMapper.createSignInCredentialJson(userName, passWord));

            return objectObservable.map(new Func1<JSONObject, String>() {
                @Override
                public String call(JSONObject jsonObject) {
                    Log.e(Tag, "call " + jsonObject);
                    //yaha receiver ka transform hota h
                    return "True";
                }
            });
        } catch (Exception m) {
            Log.e(Tag, m + "");
            return observable = Observable.just("False");
        }
    }


}
