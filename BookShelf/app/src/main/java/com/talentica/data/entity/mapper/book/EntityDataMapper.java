package com.talentica.data.entity.mapper.book;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.BooksRequestedToUserEntity;
import com.talentica.domain.model.Book;
import com.talentica.domain.model.BooksRequestedToUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class EntityDataMapper {
    private final String Tag = "BookEntityDataMapper";

    @Inject
    public EntityDataMapper() {
    }


    public Book transform(BookEntity bookEntity) {
        Book book = null;
        if (bookEntity != null) {
            book = new Book(bookEntity.getBookName(), bookEntity.getAuthersName(), bookEntity.getLenderName(), bookEntity.getBinding(), bookEntity.getPublishDate(), bookEntity.getPublisher(), bookEntity.getIsbn13Numbers(), bookEntity.getIsbn10Numbers(), bookEntity.getEdition(), bookEntity.getBookPrice());


        }

        return book;
    }

    public BookEntity transform(Book book) {
        BookEntity book1 = null;
        if (book != null) {
            book1 = new BookEntity(book.getBookName(), book.getAuthersName(), book.getLender(), book.getBinding(), book.getPublishDate(), book.getPublisher(), book.getIsbn13Numbers(), book.getIsbn10Numbers(), book.getEdition(), book.getBookPrice());


        }

        return book1;
    }

    public BooksRequestedToUserEntity transform(BooksRequestedToUser book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        BooksRequestedToUserEntity entity = new BooksRequestedToUserEntity(book.getBookName(), book.getBookAuther(), book.getRequestedBy(), book.getRequestedDate());
        return entity;
    }


    public BooksRequestedToUser transform(BooksRequestedToUserEntity entity) {
        BooksRequestedToUser book = null;
        if (entity != null) {
            book = new BooksRequestedToUser(entity.getBookName(), entity.getBookAuther(), entity.getRequestedBy(), entity.getRequestedDate());
        }

        return book;
    }

    public List<Book> transform(Collection<BookEntity> bookEntityCollection) {
        List<Book> bookList = new ArrayList<>();
        Book book;
        for (BookEntity bookEntity : bookEntityCollection) {
            book = transform(bookEntity);
            if (book != null) {
                bookList.add(book);
            }
        }

        return bookList;
    }

    public List<BooksRequestedToUser> transformFromEntity(Collection<BooksRequestedToUserEntity> entities) {
        List<BooksRequestedToUser> booksRequestedToUser = new ArrayList<>();
        BooksRequestedToUser book;
        for (BooksRequestedToUserEntity userEntity : entities) {
            book = transform(userEntity);
            if (book != null) {
                booksRequestedToUser.add(book);
            }
        }

        return booksRequestedToUser;
    }
}
