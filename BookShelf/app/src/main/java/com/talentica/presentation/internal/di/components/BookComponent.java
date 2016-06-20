
package com.talentica.presentation.internal.di.components;


import com.talentica.presentation.internal.di.PerActivity;
import com.talentica.presentation.internal.di.modules.ActivityModule;
import com.talentica.presentation.internal.di.modules.BookModule;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;

import dagger.Component;

/**
 * A scope component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BookModule.class})
public interface BookComponent extends ActivityComponent {
    void inject(HomeFragment homeFragment);

}
