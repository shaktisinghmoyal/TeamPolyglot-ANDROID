package app.src.main.java.domain.repository;


public interface HomeRepository {


    Observable<List<Books>> askForRecentlyAddedBooks();

    Observable<List<Books>> askForMostReadBooks();

    Observable<List<Books>> searchBooks();
}
