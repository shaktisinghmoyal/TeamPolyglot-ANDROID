package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ILeadCapturePageRepository;

import javax.inject.Inject;

import rx.Observable;

public class RequestToBorrowBook extends BaseUseCase {
    private final String Tag = "RequestToBorrowBook";
    private ILeadCapturePageRepository iLeadCapturePageRepository;

    @Inject
    public RequestToBorrowBook(ILeadCapturePageRepository iLeadCapturePageRepository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iLeadCapturePageRepository = iLeadCapturePageRepository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return iLeadCapturePageRepository.requestToBorrowBook(id);

    }
}
