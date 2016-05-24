package com.talentica.data.entity.mapper;

import com.talentica.data.entity.BookEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookToJsonMapper {
    static JSONObject finalJsonForBook, jsonForComment, jsonForPublisher, jsonForPublisherAddress;

    static JSONArray jsonAuthersArray;
    static JSONArray commentsArray;

    public static JSONObject getJsonObjectOfBook(BookEntity book) throws JSONException, IllegalAccessException {

        return makeJsonObjectOfBook(book);
    }

    public static JSONObject getJsonObjectOfBook(int bookId) throws JSONException, IllegalAccessException {

        //content provider se lena
        return makeJsonObjectOfBook(new BookEntity());
    }

    private static JSONObject makeJsonObjectOfBook(BookEntity book) throws JSONException, IllegalAccessException {
        finalJsonForBook = new JSONObject();
        jsonForPublisher = new JSONObject();
        jsonAuthersArray = new JSONArray();
        commentsArray = new JSONArray();
        jsonForComment = new JSONObject();
        jsonForComment.put("body", book.getCommentByOwner());
        commentsArray.put(0, jsonForComment);
        jsonForPublisher.put("name", book.getPublisher());
        jsonForPublisher.put("url", book.getPublisherURLs());

        jsonForPublisherAddress.put("street1", book.new PublisherAdress().getStreet1());
        jsonForPublisherAddress.put("street2", book.new PublisherAdress().getStreet2());
        jsonForPublisherAddress.put("country", book.new PublisherAdress().getCountry());
        jsonForPublisherAddress.put("city", book.new PublisherAdress().getCity());
        jsonForPublisherAddress.put("state", book.new PublisherAdress().getState());
        jsonForPublisherAddress.put("zip", book.new PublisherAdress().getZip());

        jsonForPublisher.put("address", jsonForPublisherAddress);


        finalJsonForBook.put("name", book.getBookName());
        finalJsonForBook.put("authors", jsonAuthersArray);
        finalJsonForBook.put("comments", commentsArray);
        finalJsonForBook.put("publisher", jsonForPublisher);
        finalJsonForBook.put("publishedOn", book.getPublishDate());
        finalJsonForBook.put("isbn13", book.getIsbn13Numbers());
        finalJsonForBook.put("isbn10", book.getIsbn10Numbers());
        finalJsonForBook.put("tags", book.getBookTags());


        return finalJsonForBook;
    }

}
