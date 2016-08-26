package com.talentica.presentation.leadCapturePage.myaccount.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.BorrowedBooksFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.MyBooksFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.ProfileFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public PagerAdapter(Fragment fragment, int pageCount) {
        super(fragment.getFragmentManager());
        this.fragmentManager = fragment.getFragmentManager();
        this.fragment = fragment;
        NUM_ITEMS = pageCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyBooksFragment();
            case 1:
                return new BorrowedBooksFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


}
