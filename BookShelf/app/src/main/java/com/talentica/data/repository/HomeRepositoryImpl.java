package app.src.main.java.data.repository;

import app.src.main.java.domain.repository.HomeRepository;

public class HomeRepositoryImpl implements HomeRepository {
    @Override
    public Observable<List<Books>> askForRecentlyAddedBooks() {
        return null;
    }

    @Override
    public Observable<List<Books>> askForMostReadBooks() {
        return null;
    }

    @Override
    public Observable<List<Books>> searchBooks() {
        return null;
    }
}
