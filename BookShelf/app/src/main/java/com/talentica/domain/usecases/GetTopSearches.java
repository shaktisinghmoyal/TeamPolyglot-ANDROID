package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.ISearchSuggestionRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetTopSearches extends BaseUseCase {
    private final String Tag = "GetTopSearches";
    private final ISearchSuggestionRepository iSearchSuggestionRepository;

    @Inject
    public GetTopSearches(ISearchSuggestionRepository iSearchSuggestionRepository, ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iSearchSuggestionRepository = iSearchSuggestionRepository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return iSearchSuggestionRepository.getTopSearches();
    }
}
