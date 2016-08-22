package com.talentica.presentation.leadCapturePage.tasks.view.activity;

import android.os.Bundle;

import com.talentica.R;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.DaggerMyTaskComponent;
import com.talentica.presentation.internal.di.components.MyTaskComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.BaseActivity;
import com.talentica.presentation.leadCapturePage.tasks.presenter.MyTaskActivityPresenter;
import com.talentica.presentation.leadCapturePage.tasks.view.MyTaskActivityView;
import com.talentica.presentation.leadCapturePage.tasks.view.fragment.MyTaskFragment;

import javax.inject.Inject;

public class MyTaskActivity extends BaseActivity implements MyTaskActivityView, HasComponent<MyTaskComponent> {
    private final String Tag = "MyTaskActivity";
    @Inject
    public MyTaskActivityPresenter presenter;
    private MyTaskComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tasks_activity);
        initializeInjector();
        component.inject(this);
        presenter.setView(this);
        presenter.initialize();

    }


    private void initializeInjector() {
        component = DaggerMyTaskComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }


    @Override
    public void setFirstFragment() {
        navigator.addFragment(this, R.id.my_tasks_fragment_container, new MyTaskFragment());
    }

    @Override
    public void updateActionBar(int id, String count) {
        getSupportActionBar().setTitle(getResources().getString(id) + " ( " + count + " ) ");
    }

    @Override
    public MyTaskComponent getComponent() {
        return component;
    }
}
