package com.talentica.data.repository;

import android.util.Log;

import com.talentica.data.entity.BooksRequestedToUserEntity;
import com.talentica.data.entity.mapper.book.EntityDataMapper;
import com.talentica.data.networking.DummyRestApi;
import com.talentica.domain.model.BooksRequestedToUser;
import com.talentica.domain.repository.IMyTaskRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class MyTaskRepository implements IMyTaskRepository {

    private final String Tag = "MyTaskRepository";
    private final EntityDataMapper dataMapper;
    private DummyRestApi dri;

    @Inject
    public MyTaskRepository(EntityDataMapper dataMapper, DummyRestApi dri) {
        this.dataMapper = dataMapper;
        this.dri = dri;
    }


    @Override
    public Observable<Boolean> bookRequestAccepted(int bookId, String userName) {
        return dri.bookRequestedAccepted(bookId, userName);
    }

    @Override
    public Observable<Boolean> bookRequestRejected(int bookId, String userName) {
        return dri.bookRequestedRejected(bookId, userName);
    }

    @Override
    public Observable<List<BooksRequestedToUser>> booksRequestedToUser() {
        return dri.bookRequestedToUser().map(new Func1<List<BooksRequestedToUserEntity>, List<BooksRequestedToUser>>() {
            @Override
            public List<BooksRequestedToUser> call(List<BooksRequestedToUserEntity> bookEntities) {
                Log.e(Tag, "recentlyAddedBookList call");
                return dataMapper.transformFromEntity(bookEntities);
            }
        });
    }
}
