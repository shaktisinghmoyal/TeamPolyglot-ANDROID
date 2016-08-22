package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IMyTaskRepository;

import javax.inject.Inject;

import rx.Observable;

public class BookRequestAccepted extends BaseUseCase {
    private final String Tag = "BookRequestAccepted";
    private IMyTaskRepository repository;

    @Inject
    public BookRequestAccepted(IMyTaskRepository repository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return repository.bookRequestAccepted(id, username);

    }
}
