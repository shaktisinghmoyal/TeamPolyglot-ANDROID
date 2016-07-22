package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISignInRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetSignInStatus extends BaseUseCase {
    private final String Tag = "GetSignInStatus";
    private final ISignInRepository iSignInRepository;

    @Inject
    public GetSignInStatus(ISignInRepository iSignInRepository, ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iSignInRepository = iSignInRepository;
    }



    @Override
    public Observable buildUseCaseObservable() {
        return iSignInRepository.tryForSignIn(username, password);
    }
}
