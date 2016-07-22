package com.talentica.data.entity.mapper.authentication;

import org.json.JSONException;
import org.json.JSONObject;

public class CredentialToJsonMapper {
    static JSONObject finalObject;
    private final String Tag = "CredentialToJsonMapper";

    public static JSONObject createSignInCredentialJson(String username, String password) throws JSONException, IllegalAccessException {
        finalObject = new JSONObject();
        finalObject.put("username", "reshakt");
        finalObject.put("password", "SHAmoy123");

        return finalObject;
    }
}
