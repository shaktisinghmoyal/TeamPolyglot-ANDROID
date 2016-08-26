package com.talentica.presentation.navigation;
//import javax.inject.Inject;
//import javax.inject.Singleton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.home.view.activity.BookDetailActivity;
import com.talentica.presentation.leadCapturePage.home.view.activity.ListAllActivity;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;
import javax.inject.Singleton;
/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {
    private final String Tag = "Navigator";
    @Inject
    public Navigator() {
        //empty
    }

    public void startBookDetailActivity(AppCompatActivity from, Bundle bundle) {
        Intent intent = new Intent(from, BookDetailActivity.class);
        intent.putExtra(Util.bookDetailBundle, bundle);
        from.startActivity(intent);

    }

    public void startAnotherActivity(AppCompatActivity from, Intent intent) {
        //popEveryFragment( from);
        from.startActivityForResult(intent, Util.REQUEST_CODE_ADD_BOOK_ACTIVITY);

    }

    public void startListAllActivity(AppCompatActivity from, Bundle bundle) {
        Intent intent = new Intent(from, ListAllActivity.class);
        intent.putExtra(Util.fragmentTitle, bundle);
        from.startActivity(intent);

    }

    public void addFragment(AppCompatActivity activity, int containerViewId, Fragment fragment) {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        Log.e(Tag, "" + " simply addFragment");
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
        activity.getSupportFragmentManager().executePendingTransactions();
    }

    public void addFragment(AppCompatActivity activity, int containerViewId, Fragment fragment, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (tag.equals(Util.ENTRY)) {
            Log.e(Tag, "" + "add first Fragment  ");
            fragmentTransaction.replace(containerViewId, fragment, tag);
        } else {
            Log.e(Tag, "" + "addFragment  ");

            fragmentTransaction.replace(containerViewId, fragment, tag).addToBackStack(tag);

        }

        fragmentTransaction.commit();
        activity.getSupportFragmentManager().executePendingTransactions();

    }

    public void openAsRoot(AppCompatActivity activity, int containerViewId, Fragment fragment, String tag) {
        popEveryFragment(activity);
        addFragment(activity, containerViewId, fragment, tag);
    }

    public void openAsMainRoot(AppCompatActivity activity) {
        popEveryFragment(activity);
        // addFragment( activity, containerViewId,  fragment, tag);
    }

    public void popEveryFragment(AppCompatActivity activity) {
        // Clear all back stack.
        FragmentManager manager = activity.getSupportFragmentManager();
        int backStackCount = manager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {

            // Get the back stack fragment id.
            int backStackId = manager.getBackStackEntryAt(i).getId();

            manager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
    }
}
