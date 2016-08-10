package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IAddBookRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetBooksToAdd extends BaseUseCase {
    private final String Tag = "GetBooksToAdd";
    private final IAddBookRepository iAddBookRepository;

    @Inject
    public GetBooksToAdd(IAddBookRepository iAddBookRepository, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iAddBookRepository = iAddBookRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return iAddBookRepository.elasticSearchForBooks(recentSearchString);
    }
}