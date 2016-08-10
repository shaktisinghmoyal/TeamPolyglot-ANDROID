package com.talentica.presentation.navigation;
//import javax.inject.Inject;
//import javax.inject.Singleton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.talentica.presentation.leadCapturePage.home.view.acitivity.BookDetailActivity;
import com.talentica.presentation.leadCapturePage.home.view.acitivity.ListAllActivity;
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
        from.startActivity(intent);

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

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (tag.equals("first")) {
            Log.e(Tag, "" + "add first Fragment  ");
            fragmentTransaction.replace(containerViewId, fragment, tag);
        } else {
            Log.e(Tag, "" + "addFragment  ");
            fragmentTransaction.replace(containerViewId, fragment, tag).addToBackStack(tag);

        }

        fragmentTransaction.commit();
        activity.getSupportFragmentManager().executePendingTransactions();
    }
    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
//    public void navigateToUserList(Context context) {
////        if (context != null) {
////            Intent intentToLaunch = UserListActivity.getCallingIntent(context);
////            context.startActivity(intentToLaunch);
////        }
//    }
//
//    /**
//     * Goes to the user details screen.
//     *
//     * @param context A Context needed to open the destiny activity.
//     */
//    public void navigateToUserDetails(Context context, int userId) {
////        if (context != null) {
////            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
////            context.startActivity(intentToLaunch);
////        }
//    }
}
