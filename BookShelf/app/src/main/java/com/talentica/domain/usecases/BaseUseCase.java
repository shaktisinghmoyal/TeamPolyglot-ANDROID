/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.talentica.domain.usecases;


import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p/>
 * By convention each UseCase implementation will return the result using a Subscriber
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class BaseUseCase {

    final ThreadExecutor threadExecutor;
    final PostExecutionThread postExecutionThread;
    String username;
    String password;
    String fullName;
    Subscription subscription = Subscriptions.empty();
    private Subscriber useCaseSubscriber;

    protected BaseUseCase(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an Observable which will be used when executing the current UseCase.
     */
    public abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param UseCaseSubscriber The guy who will be listen to the observable build
     *                          with {@link #buildUseCaseObservable()}.
     */

    @SuppressWarnings("unchecked")
    public void execute(Subscriber UseCaseSubscriber) {
        useCaseSubscriber = UseCaseSubscriber;
        execute();
    }

    @SuppressWarnings("unchecked")
    public void execute(String email, Subscriber UseCaseSubscriber) {
        useCaseSubscriber = UseCaseSubscriber;
        this.username = email;
        execute();
    }

    @SuppressWarnings("unchecked")
    public void execute(String username, String password, Subscriber UseCaseSubscriber) {
        this.username = username;
        this.password = password;
        useCaseSubscriber = UseCaseSubscriber;
        execute();
    }

    @SuppressWarnings("unchecked")
    public void execute(String username, String password, String fullName, Subscriber UseCaseSubscriber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        useCaseSubscriber = UseCaseSubscriber;
        execute();
    }


    @SuppressWarnings("unchecked")
    public void execute() {
        subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);

    }

    /**
     * Unsubscribes from current Subscription.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
