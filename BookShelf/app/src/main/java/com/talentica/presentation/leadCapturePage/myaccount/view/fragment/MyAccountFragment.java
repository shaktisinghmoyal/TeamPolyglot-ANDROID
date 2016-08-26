package com.talentica.presentation.leadCapturePage.myaccount.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.MyAccountLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.myaccount.presenter.MyAccountPresenter;
import com.talentica.presentation.leadCapturePage.myaccount.view.MyAccountView;
import com.talentica.presentation.leadCapturePage.myaccount.view.adapter.PagerAdapter;
import com.talentica.presentation.utils.Enums;

import javax.inject.Inject;

public class MyAccountFragment extends BaseFragment implements MyAccountView, TabLayout.OnTabSelectedListener {

    private final String Tag = "MyAccountFragment";

    @Inject
    MyAccountPresenter presenter;
    PagerAdapter adapterViewPager;
    MyAccountLayoutBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.my_account_layout, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            initializeItems();
        }
        presenter.setView(this);
        presenter.initialize();


    }

    private void initializeItems() {

        tabLayout = binding.tabLayout;

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.my_books)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.books_i_borrowed)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.profile)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(this);
        viewPager = binding.viewPager;
        adapterViewPager = new PagerAdapter(this, tabLayout.getTabCount());
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }


    @Override
    public void setActionBarTitle() {
        ((MainActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.MY_ACCOUNT, getResources().getString(R.string.my_account));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
