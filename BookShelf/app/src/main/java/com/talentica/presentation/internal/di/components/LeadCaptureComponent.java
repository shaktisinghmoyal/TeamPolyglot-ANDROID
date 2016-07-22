package com.talentica.presentation.internal.di.components;

import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.LeadCaptureModule;
import com.talentica.presentation.leadCapturePage.base.view.MainActivity;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BookDetailFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BooksGridViewFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.SearchFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LeadCaptureModule.class})
public interface LeadCaptureComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);

    void inject(BookDetailFragment bookDetailFragment);

    void inject(SearchFragment searchFragment);

    void inject(BooksGridViewFragment booksGridViewFragment);
}
