package com.talentica.presentation.internal.di.modules;


import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.GetBooksGridResults;
import com.talentica.domain.usecases.GetMostReadBookList;
import com.talentica.domain.usecases.GetRecentSearches;
import com.talentica.domain.usecases.GetRecentlyAddedBookList;
import com.talentica.domain.usecases.GetTopSearches;
import com.talentica.domain.usecases.SaveRecentSearches;
import com.talentica.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LeadCaptureModule {

    public LeadCaptureModule() {
    }

    @Provides
    @PerActivity
    @Named("saveRecentSearches")
    BaseUseCase provideSaveRecentSearchesBaseUseCase(SaveRecentSearches saveRecentSearches) {
        return saveRecentSearches;
    }

    @Provides
    @PerActivity
    @Named("recentlyAddedBookList")
    BaseUseCase provideGetRecentlyAddedBookListBaseUseCase(GetRecentlyAddedBookList getRecentlyAddedBookList) {
        return getRecentlyAddedBookList;
    }

    @Provides
    @PerActivity
    @Named("mostReadBookList")
    BaseUseCase provideGetMostReadBookListBaseUseCase(GetMostReadBookList getMostReadBookList) {
        return getMostReadBookList;
    }

    @Provides
    @PerActivity
    @Named("getTopSearchesUseCase")
    BaseUseCase provideGetTopSearchesBaseUseCase(GetTopSearches getTopSearches) {
        return getTopSearches;
    }

    @Provides
    @PerActivity
    @Named("getRecentSearchesUseCase")
    BaseUseCase provideGetRecentSearchesBaseUseCase(GetRecentSearches getRecentSearches) {
        return getRecentSearches;
    }

    @Provides
    @PerActivity
    @Named("getBookGridResultsUseCase")
    BaseUseCase provideGetBookGridResultsBaseUseCase(GetBooksGridResults getBooksGridResults) {
        return getBooksGridResults;
    }


}
