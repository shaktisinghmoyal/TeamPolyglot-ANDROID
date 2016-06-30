package com.talentica.data.networking.volley;


import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.talentica.presentation.BookShelfApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;


public class VolleyApiRequest {
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json";
    // Tag used to cancel the request
    private String TAG = "VolleyApiRequest";
    private String tag_json_obj = "json_obj_req";
    private String url;
    private JSONObject response;
    private ReplaySubject<Observable<JSONObject>> replaySubject = ReplaySubject.create();
    private Observable<JSONObject> jsonObjectObservable;

    private VolleyApiRequest(String url) throws MalformedURLException {
        this.url = url;
    }

    public static VolleyApiRequest createVolleyApiRequestObject(String url) throws MalformedURLException {

        return new VolleyApiRequest(url);
    }


    public Observable<JSONObject> VolleyJsonRequest(int method, JSONObject jsonObject) {
        Log.e(TAG, "VolleyJsonRequest: " + " jsonObject " + jsonObject);
        JsonRequest(method, jsonObject);
        Log.e(TAG, "VolleyJsonRequest: " + "VolleyJsonRequest called");

        return replaySubject.flatMap(new Func1<Observable<JSONObject>, Observable<JSONObject>>() {
            @Override
            public Observable<JSONObject> call(Observable<JSONObject> jsonObjectObservable) {
                Log.d(TAG, "getObservable: " + "getObservable called" + jsonObjectObservable);
                return jsonObjectObservable;
            }
        });
    }

    public void JsonRequest(int method, JSONObject jsonObject) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        replaySubject.onNext(Observable.just(response));


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                // publishSubject.onNext(Observable.just(j));
                //replaySubject.onNext(Observable.just(j));

                // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                Log.e(TAG, "onErrorResponse " + error.toString() + " response   " + response);
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e(TAG, "JSONObject " + obj);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }

                replaySubject.onError(new Throwable("VolleyError"));
            }
        });
//        }) {
//
//            /**
//             * Passing some request headers
//             * */
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                HashMap<String, String> headers = new HashMap<String, String>();
////                headers.put("Content-Type", "application/json");
////                headers.put("apiKey", "xxxxxxxxxxxxxxx");
////                return headers;
////            }
//
//        };


        Log.e(TAG, "jsonObjReq: " + jsonObjReq);
        VolleySingleton.getInstance(BookShelfApplication.getAppContext()).addToRequestQueue(jsonObjReq, tag_json_obj);
    }


}
