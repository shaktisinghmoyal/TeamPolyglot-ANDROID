package app.src.main.java.data.networking;

public class RestApiImpl implements RestApi, BaseURL {
    @Override
    public Observable<List<Book>> recentlyAddedBookList() {
        return null;
    }

    @Override
    public Observable<List<Book>> mostReadBookList() {
        return null;
    }

    @Override
    public Observable<List<Book>> abstractSearch(String stringForSearch) {
        return null;
    }
}
