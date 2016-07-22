package com.talentica.data.repository;


import android.util.Log;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.book.BookEntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.model.Book;
import com.talentica.domain.repository.IHomeRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class HomeRepository implements IHomeRepository {

    private final String Tag = "HomeRepository";
    private final BookEntityDataMapper bookEntityDataMapper;
    private DummyRestApi dri;

    @Inject
    public HomeRepository(BookEntityDataMapper bookEntityDataMapper, DummyRestApi dri) {
        this.bookEntityDataMapper = bookEntityDataMapper;
        this.dri = dri;

    }

    @Override
    public Observable<List<Book>> askForRecentlyAddedBooks() {
//        Log.e("HomeRepository", "askForRecentlyAddedBooks");

        return dri.recentlyAddedBookList().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                Log.e(Tag, "recentlyAddedBookList call");
                return bookEntityDataMapper.transform(bookEntities);
            }
        });
    }

    @Override
    public Observable<List<Book>> askForMostReadBooks() {
//        Log.e("HomeRepository", "askForMostReadBooks");
        return dri.mostReadBookList().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                Log.e(Tag, "askForMostReadBooks map call");
                return bookEntityDataMapper.transform(bookEntities);
            }
        });
    }

    @Override
    public Observable<List<Book>> searchBooks() {
        return dri.predictiveSearch().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                Log.e(Tag, "searchBooks map call");
                return bookEntityDataMapper.transform(bookEntities);
            }
        });
    }
}
