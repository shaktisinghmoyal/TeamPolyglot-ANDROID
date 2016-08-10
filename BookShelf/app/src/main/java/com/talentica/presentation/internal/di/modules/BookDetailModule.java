package com.talentica.presentation.internal.di.modules;


import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.RequestToBorrowBook;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailModule {

    public BookDetailModule() {
    }

    @Provides
    @PerActivity
    @Named("bookBorrowRequest")
    BaseUseCase provideRequestToBorrowBookBaseUseCase(RequestToBorrowBook requestToBorrowBook) {
        return requestToBorrowBook;
    }
}
