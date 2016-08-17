package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ILeadCapturePageRepository;

import javax.inject.Inject;

import rx.Observable;

public class SaveRecentSearches extends BaseUseCase {

    private final String Tag="SaveSearches";
    private ILeadCapturePageRepository iLeadCapturePageRepository;
    @Inject
    public SaveRecentSearches(ILeadCapturePageRepository iLeadCapturePageRepository, ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.iLeadCapturePageRepository = iLeadCapturePageRepository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        iLeadCapturePageRepository.saveRecentSearch(recentSearchString);
        return Observable.just("savedRecentSearch");

    }
}