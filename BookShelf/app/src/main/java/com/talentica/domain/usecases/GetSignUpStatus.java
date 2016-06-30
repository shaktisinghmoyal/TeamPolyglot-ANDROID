package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISignUpRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetSignUpStatus extends BaseUseCase {

    private final ISignUpRepository iSignUpRepository;


    @Inject
    public GetSignUpStatus(ISignUpRepository iSignUpRepository, ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iSignUpRepository = iSignUpRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return iSignUpRepository.tryForSignUp(username, password, fullName);
    }
}
