package com.talentica.presentation.internal.di.components;


import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.BookDetailModule;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.BookDetailActivity;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BookDetailFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BookDetailModule.class})
public interface BookDetailComponent extends ActivityComponent {

    void inject(BookDetailActivity bookDetailActivity);

    void inject(BookDetailFragment bookDetailFragment);
}
