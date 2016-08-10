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
package com.talentica.presentation.internal.di.components;

import android.content.Context;

import com.talentica.domain.executor.PostExecutionThread;
import com.talentica.domain.executor.ThreadExecutor;
import com.talentica.domain.repository.IAddBookRepository;
import com.talentica.domain.repository.IHomeRepository;
import com.talentica.domain.repository.ILeadCapturePageRepository;
import com.talentica.domain.repository.IResetPassRepository;
import com.talentica.domain.repository.ISearchSuggestionRepository;
import com.talentica.domain.repository.ISignInRepository;
import com.talentica.domain.repository.ISignUpRepository;
import com.talentica.presentation.internal.di.modules.ApplicationModule;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    IHomeRepository homeRepository();

    ISignInRepository signInRepository();

    ISignUpRepository signUpRepository();

    IResetPassRepository resetPassRepository();

    ISearchSuggestionRepository searchSuggestionRepository();

    ILeadCapturePageRepository leadCapturePageRepository();

    IAddBookRepository addBookRepository();


}
