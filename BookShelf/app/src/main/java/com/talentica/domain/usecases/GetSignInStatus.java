package com.talentica.domain.usecases;

import android.util.Log;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISignInRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class GetSignInStatus extends BaseUseCase {

    private final ISignInRepository iSignInRepository;
    private String username;
    private String password;

    @Inject
    public GetSignInStatus(ISignInRepository iSignInRepository, ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iSignInRepository = iSignInRepository;
    }

    @SuppressWarnings("unchecked")
    public void execute(String username, String password, Subscriber UseCaseSubscriber) {
        Log.e("overriden execute", "called ");

        this.username = username;
        this.password = password;
        //Log.e("execute", "called");
        subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(UseCaseSubscriber);

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return iSignInRepository.tryForSignIn(username, password);
    }
}
