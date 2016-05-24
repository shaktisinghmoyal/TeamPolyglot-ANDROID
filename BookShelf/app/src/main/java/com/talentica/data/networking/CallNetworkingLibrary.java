package com.talentica.data.networking;

import com.talentica.data.networking.volley.VolleyApiRequest;

import org.json.JSONObject;

import java.net.MalformedURLException;

public class CallNetworkingLibrary {

    static JSONObject callApiRequest(String URL, int method, JSONObject objectToSend) throws MalformedURLException {

        return VolleyApiRequest.createVolleyApiRequestObject(URL).VolleyJsonRequest(method, objectToSend);
    }
}
