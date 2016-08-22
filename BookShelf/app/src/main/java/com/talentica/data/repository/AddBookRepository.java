package com.talentica.data.repository;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.book.EntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.model.Book;
import com.talentica.domain.repository.IAddBookRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class AddBookRepository implements IAddBookRepository {
    private final String Tag = "AddBookRepository";
    private final EntityDataMapper entityDataMapper;
    private DummyRestApi dri;

    @Inject
    public AddBookRepository(EntityDataMapper bookEntityDataMapper, DummyRestApi dri) {
        this.entityDataMapper = bookEntityDataMapper;
        this.dri = dri;

    }

    @Override
    public Observable<List<Book>> elasticSearchForBooks(String query) {
        return dri.predictiveSearch().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                return entityDataMapper.transform(bookEntities);
            }
        });
    }

    @Override
    public Observable<Boolean> submitBook(Book book) {

        BookEntity bookEntity = entityDataMapper.transform(book);
        return dri.submitBook(bookEntity).map(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean submitted) {
                return submitted;
            }
        });
    }
}
