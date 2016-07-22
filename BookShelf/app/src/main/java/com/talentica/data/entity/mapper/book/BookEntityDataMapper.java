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
            book = new Book(bookEntity.getBookName(), bookEntity.getAuthersName(), bookEntity.getLenderName());


        }

        return book;
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
