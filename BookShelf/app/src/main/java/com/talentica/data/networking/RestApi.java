package app.src.main.java.data.networking;

import java.util.List;

public interface RestApi extends BaseURL {
    //for book report operations
    String API_RECENTLY_ADDED_BOOK = API_BASE_URL + API_SEPERATOR + "recently-added";
    String API_MOST_READ_BOOK = API_BASE_URL + API_SEPERATOR + "most-read";
    //for book abstract search  operation
    String API_ABSTRACT_BOOK_SEARCH = API_BASE_URL + API_SEPERATOR + "search";


    Observable<List<Book>> recentlyAddedBookList();

    Observable<List<Book>> mostReadBookList();

    Observable<List<Book>> abstractSearch(final String stringForSearch);
}
