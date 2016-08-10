package com.talentica.presentation.internal.di.modules;

import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.GetBooksToAdd;
import com.talentica.domain.usecases.SubmitMyBook;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AddBookModule {

    public AddBookModule() {

    }

    @Provides
    @PerActivity
    @Named("getElasticBookSearchUseCase")
    BaseUseCase provideGetBooksToAddBaseUseCase(GetBooksToAdd getBooksToAdd) {
        return getBooksToAdd;
    }

    @Provides
    @PerActivity
    @Named("submitBookUseCase")
    BaseUseCase provideSubmitMyBookBaseUseCase(SubmitMyBook submitMyBook) {
        return submitMyBook;
    }
}
