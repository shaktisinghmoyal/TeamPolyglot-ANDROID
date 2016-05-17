package app.src.main.java.data.networking;


import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.net.MalformedURLException;

import talentica.com.booksharingapp.presentation.BookApplication;


public class VolleyApiRequest {
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json";
    // Tag used to cancel the request
    private String TAG = "VolleyApiRequest";
    private String tag_json_obj = "json_obj_req";
    private String url;
    private JSONObject response;

    private VolleyApiRequest(String url) throws MalformedURLException {
        this.url = url;
    }

    public static VolleyApiRequest createVolleyApiRequestObject(String url) throws MalformedURLException {

        return new VolleyApiRequest(url);
    }


    public JSONObject VolleyJsonRequest(int method, JSONObject jsonObject) {
        JsonRequest(method, jsonObject);
        return response;
    }

    public void JsonRequest(int method, JSONObject jsonObject) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Log.d(TAG, response.toString());
                        getResponse(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Volley Api Response Error: " + error.getMessage());

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

        VolleySingleton.getInstance(BookApplication.getAppContext()).addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getResponse(JSONObject response) {
        this.response = response;
    }


}
