package com.talentica.presentation.internal.di.components;

import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.LeadCaptureModule;
import com.talentica.presentation.internal.di.modules.MyAccountModule;
import com.talentica.presentation.internal.di.modules.MyTaskModule;
import com.talentica.presentation.internal.di.modules.NotificationModule;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.home.view.activity.ListAllActivity;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BooksGridViewFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.SearchFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.BorrowedBooksFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.MyAccountFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.MyBooksFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.ProfileFragment;
import com.talentica.presentation.leadCapturePage.notifications.view.fragment.NotificationFragment;
import com.talentica.presentation.leadCapturePage.tasks.view.fragment.MyTaskFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LeadCaptureModule.class, MyTaskModule.class, NotificationModule.class, MyAccountModule.class})
public interface LeadCaptureComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(ListAllActivity listAllActivity);

    void inject(HomeFragment homeFragment);

    void inject(SearchFragment searchFragment);

    void inject(BooksGridViewFragment booksGridViewFragment);

    void inject(MyTaskFragment myTaskFragment);

    void inject(NotificationFragment notificationFragment);

    void inject(MyAccountFragment myAccountFragment);

    void inject(BorrowedBooksFragment borrowedBooksFragment);

    void inject(ProfileFragment profileFragment);

    void inject(MyBooksFragment profileFragment);
}
