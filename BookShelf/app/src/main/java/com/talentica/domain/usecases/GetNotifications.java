package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.INotificationRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetNotifications extends BaseUseCase {
    private final String Tag = "GetTasks";
    private INotificationRepository repository;

    @Inject
    public GetNotifications(INotificationRepository repository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return repository.getNotifications();
    }
}
