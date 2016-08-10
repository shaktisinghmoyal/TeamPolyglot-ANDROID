
package com.talentica.presentation.mapper;


import com.talentica.domain.model.Book;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform Book (in the domain layer) to { BookModel} in the
 * presentation layer.
 */
@PerActivity
public class BookModelDataMapper {


    private final String Tag = "BookModelDataMapper";
    @Inject
    public BookModelDataMapper() {
    }

    /**
     * Transform a  Book into an  BookModel.
     *
     * @param book Object to be transformed.
     * @return BookModel.
     */
    public BookModel transform(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        BookModel bookModel = new BookModel(book.getBookName(), book.getAuthersName(), book.getLender(), book.getBinding(), book.getPublishDate(), book.getPublisher(), book.getIsbn13Numbers(), book.getIsbn10Numbers(), book.getEdition(), book.getBookPrice());
//    userModel.setAuthersName(book.getAuthersName());
//    bookModel.setBookName(book.getBookName());
//    bookModel.setLender(book.getLender());


        return bookModel;
    }

    public Book transform(BookModel bookModel) {
        Book book = null;
        if (bookModel != null) {
            book = new Book(bookModel.getBookName(), bookModel.getAuthersName(), bookModel.getLender(), bookModel.getBinding(), bookModel.getPublishDate(), bookModel.getPublisher(), bookModel.getIsbn13Numbers(), bookModel.getIsbn10Numbers(), bookModel.getEdition(), bookModel.getBookPrice());


        }

        return book;
    }

    /**
     * Transform a Collection of {@link Book} into a Collection of {@link BookModel}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link BookModel}.
     */
    public Collection<BookModel> transform(Collection<Book> usersCollection) {
        Collection<BookModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<BookModel>();
            for (Book user : usersCollection) {
                userModelsCollection.add(transform(user));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
