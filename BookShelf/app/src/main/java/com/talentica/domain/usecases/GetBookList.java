
package com.talentica.domain.usecases;


import android.util.Log;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IHomeRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {BaseUseCase} that represents a use case for
 * retrieving a collection of all {Book}.
 */
public class GetBookList extends BaseUseCase {

    private final IHomeRepository iHomeRepository;

    @Inject
    public GetBookList(IHomeRepository iHomeRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.iHomeRepository = iHomeRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        Log.e("GetBookList", "buildUseCaseObservable");
        return this.iHomeRepository.askForRecentlyAddedBooks();
    }
}
