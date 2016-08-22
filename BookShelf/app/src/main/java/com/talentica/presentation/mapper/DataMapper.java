
package com.talentica.presentation.mapper;


import com.talentica.domain.model.Book;
import com.talentica.domain.model.BooksRequestedToUser;
import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform Book (in the domain layer) to { BookModel} in the
 * presentation layer.
 */
@PerActivity
public class DataMapper {


    private final String Tag = "BookModelDataMapper";
    @Inject
    public DataMapper() {
    }


    public BookModel transform(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        BookModel bookModel = new BookModel(book.getBookName(), book.getAuthersName(), book.getLender(), book.getBinding(), book.getPublishDate(), book.getPublisher(), book.getIsbn13Numbers(), book.getIsbn10Numbers(), book.getEdition(), book.getBookPrice());
        return bookModel;
    }

    public Book transform(BookModel bookModel) {
        Book book = null;
        if (bookModel != null) {
            book = new Book(bookModel.getBookName(), bookModel.getAuthersName(), bookModel.getLender(), bookModel.getBinding(), bookModel.getPublishDate(), bookModel.getPublisher(), bookModel.getIsbn13Numbers(), bookModel.getIsbn10Numbers(), bookModel.getEdition(), bookModel.getBookPrice());
        }

        return book;
    }


    public BooksRequestedToUserModel transform(BooksRequestedToUser book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        BooksRequestedToUserModel bookModel = new BooksRequestedToUserModel(book.getBookName(), book.getBookAuther(), book.getRequestedBy(), book.getRequestedDate());
        return bookModel;
    }


    public BooksRequestedToUser transform(BooksRequestedToUserModel bookModel) {
        BooksRequestedToUser book = null;
        if (bookModel != null) {
            book = new BooksRequestedToUser(bookModel.getBookName(), bookModel.getBookAuther(), bookModel.getRequestedBy(), bookModel.getRequestedDate());
        }

        return book;
    }


    public Collection<BookModel> transform(Collection<Book> bookCollection) {
        Collection<BookModel> bookModelsCollection;

        if (bookCollection != null && !bookCollection.isEmpty()) {
            bookModelsCollection = new ArrayList<BookModel>();
            for (Book book : bookCollection) {
                bookModelsCollection.add(transform(book));
            }
        } else {
            bookModelsCollection = Collections.emptyList();
        }

        return bookModelsCollection;
    }


    public Collection<BooksRequestedToUserModel> transformRequestedBooks(Collection<BooksRequestedToUser> requestedBooksCollection) {
        Collection<BooksRequestedToUserModel> requestedBooksModelCollection;

        if (requestedBooksCollection != null && !requestedBooksCollection.isEmpty()) {
            requestedBooksModelCollection = new ArrayList<BooksRequestedToUserModel>();
            for (BooksRequestedToUser book : requestedBooksCollection) {
                requestedBooksModelCollection.add(transform(book));
            }
        } else {
            requestedBooksModelCollection = Collections.emptyList();
        }

        return requestedBooksModelCollection;
    }
}
