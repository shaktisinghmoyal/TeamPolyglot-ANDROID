package com.talentica.presentation.internal.di.components;

import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.AddBookModule;
import com.talentica.presentation.leadCapturePage.addmybook.view.activity.AddMyBookActivity;
import com.talentica.presentation.leadCapturePage.addmybook.view.fragment.AddBookDetailFragment;
import com.talentica.presentation.leadCapturePage.addmybook.view.fragment.AddBookMainFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AddBookModule.class})
public interface AddBookComponent extends ActivityComponent {
    void inject(AddMyBookActivity listAllActivity);

    void inject(AddBookMainFragment addBookMainFragment);

    void inject(AddBookDetailFragment addBookManuallyFragment);
}
