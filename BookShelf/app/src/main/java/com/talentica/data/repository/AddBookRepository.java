package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.book.BookEntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.model.Book;
import com.talentica.domain.repository.IAddBookRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class AddBookRepository implements IAddBookRepository {
    private final String Tag = "AddBookRepository";
    private final BookEntityDataMapper bookEntityDataMapper;
    private DummyRestApi dri;

    @Inject
    public AddBookRepository(BookEntityDataMapper bookEntityDataMapper, DummyRestApi dri) {
        this.bookEntityDataMapper = bookEntityDataMapper;
        this.dri = dri;

    }

    @Override
    public Observable<List<Book>> elasticSearchForBooks(String query) {
        return dri.predictiveSearch().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                Log.e(Tag, "searchBooks map call");
                return bookEntityDataMapper.transform(bookEntities);
            }
        });
    }

    @Override
    public Observable<Boolean> submitBook(Book book) {

        BookEntity bookEntity = bookEntityDataMapper.transform(book);

        return dri.submitBook(bookEntity).map(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean submitted) {
                Log.e(Tag, "submitted");
                return submitted;
            }
        });
    }
}
