package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetMostReadBookList extends BaseUseCase {


    private final IHomeRepository iHomeRepository;

    @Inject
    public GetMostReadBookList(IHomeRepository iHomeRepository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iHomeRepository = iHomeRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return iHomeRepository.askForMostReadBooks();
    }
}
