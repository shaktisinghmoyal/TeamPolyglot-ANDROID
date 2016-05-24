package com.talentica.data.repository;


import android.util.Log;

import com.talentica.data.entity.BookEntity;
import com.talentica.data.entity.mapper.BookEntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.model.Book;
import com.talentica.domain.repository.IHomeRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class HomeRepository implements IHomeRepository {

    private final BookEntityDataMapper bookEntityDataMapper;

    @Inject
    public HomeRepository(BookEntityDataMapper bookEntityDataMapper) {
        this.bookEntityDataMapper = bookEntityDataMapper;
    }

    @Override
    public Observable<List<Book>> askForRecentlyAddedBooks() {
        Log.e("HomeRepository", "askForRecentlyAddedBooks");
        DummyRestApi dri = new DummyRestApi();
        return dri.recentlyAddedBookList().map(new Func1<List<BookEntity>, List<Book>>() {
            @Override
            public List<Book> call(List<BookEntity> bookEntities) {
                Log.e("HomeRepository", "call");
                return bookEntityDataMapper.transform(bookEntities);
            }
        });
    }

    @Override
    public Observable<List<Book>> askForMostReadBooks() {
        return null;
    }

    @Override
    public Observable<List<Book>> searchBooks() {
        return null;
    }
}
