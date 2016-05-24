package com.talentica.data.entity.mapper;

import com.talentica.data.entity.BookEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookEntityJsonMapper {

    public static boolean getStatus(JSONObject jsonObject) throws JSONException, IllegalAccessException {

        return jsonObject.getBoolean("status");
    }

    public static BookEntity getRetrievedBook(JSONObject jsonObject) throws JSONException, IllegalAccessException {


        return new BookEntity();
    }


    public static List<BookEntity> getRetrievedBookList(JSONObject jsonObject) throws JSONException, IllegalAccessException {


        return new ArrayList<BookEntity>();
    }

    public static String getBookStatus(JSONObject jsonObject) throws JSONException, IllegalAccessException {

        return "";
    }
}
