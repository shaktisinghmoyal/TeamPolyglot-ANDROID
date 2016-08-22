package com.talentica.presentation.internal.di.components;


import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.MyTaskModule;
import com.talentica.presentation.leadCapturePage.tasks.view.activity.MyTaskActivity;
import com.talentica.presentation.leadCapturePage.tasks.view.fragment.MyTaskFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MyTaskModule.class})
public interface MyTaskComponent extends ActivityComponent {
    void inject(MyTaskActivity myTaskActivity);

    void inject(MyTaskFragment myTaskFragment);
}
