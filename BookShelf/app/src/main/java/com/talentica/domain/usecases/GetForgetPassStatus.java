package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IResetPassRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetForgetPassStatus extends BaseUseCase {

    private final IResetPassRepository iResetPassRepository;


    @Inject
    public GetForgetPassStatus(IResetPassRepository iResetPassRepository, ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iResetPassRepository = iResetPassRepository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return iResetPassRepository.tryForResetPass(username);
    }
}
