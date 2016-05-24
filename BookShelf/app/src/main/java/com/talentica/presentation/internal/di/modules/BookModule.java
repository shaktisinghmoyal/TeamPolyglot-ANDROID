
package com.talentica.presentation.internal.di.modules;


import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.GetBookList;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class BookModule {

    private int bookId = -1;

    public BookModule() {
    }

    public BookModule(int bookId) {
        this.bookId = bookId;
    }

    @Provides
    @PerActivity
    @Named("bookList")
    BaseUseCase provideGetBookListBaseUseCase(
            GetBookList getBookList) {
        return getBookList;
    }


}