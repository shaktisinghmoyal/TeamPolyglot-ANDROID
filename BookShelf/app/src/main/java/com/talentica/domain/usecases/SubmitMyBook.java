package com.talentica.domain.usecases;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IAddBookRepository;

import javax.inject.Inject;

import rx.Observable;

public class SubmitMyBook extends BaseUseCase {

    private final String Tag = "SubmitMyBook";
    private IAddBookRepository iAddBookRepository;

    @Inject
    public SubmitMyBook(IAddBookRepository iAddBookRepository, ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iAddBookRepository = iAddBookRepository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return iAddBookRepository.submitBook(book);

    }
}