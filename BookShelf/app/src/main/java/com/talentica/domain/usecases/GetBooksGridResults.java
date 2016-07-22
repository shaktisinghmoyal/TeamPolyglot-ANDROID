package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetBooksGridResults extends BaseUseCase {
    private final String Tag = "GetBooksGridResults";
    private final IHomeRepository iHomeRepository;

    @Inject
    public GetBooksGridResults(IHomeRepository iHomeRepository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iHomeRepository = iHomeRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return iHomeRepository.searchBooks();
    }
}
