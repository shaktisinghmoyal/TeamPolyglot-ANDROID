package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IMyTaskRepository;

import javax.inject.Inject;

import rx.Observable;

public class BookRequestRejected extends BaseUseCase {
    private final String Tag = "BookRequestRejected";
    private IMyTaskRepository repository;

    @Inject
    public BookRequestRejected(IMyTaskRepository repository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return repository.bookRequestRejected(id, username);

    }
}
