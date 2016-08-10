package com.talentica.data.entity.mapper.book;

import com.talentica.data.entity.BookEntity;
import com.talentica.domain.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class BookEntityDataMapper {
    private final String Tag = "BookEntityDataMapper";

    @Inject
    public BookEntityDataMapper() {
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


    public List<Book> transform(Collection<BookEntity> userEntityCollection) {
        List<Book> userList = new ArrayList<>();
        Book book;
        for (BookEntity userEntity : userEntityCollection) {
            book = transform(userEntity);
            if (book != null) {
                userList.add(book);
            }
        }

        return userList;
    }
}
