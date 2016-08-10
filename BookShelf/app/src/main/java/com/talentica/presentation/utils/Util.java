package com.talentica.presentation.utils;

import android.util.DisplayMetrics;

import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;


public class Util {

    public static final String fragmentTitle = "FRAGMENT_TITLE";
    public static DisplayMetrics dm = new DisplayMetrics();
    public static String bookDetailBundle = "BOOK_DETAIL_BUNDLE";
    public static String fragmentDetailBundle = "FRAGMENT_DETAIL_BUNDLE";
    public static String fillDetailType = "FILL_DETAIL_TYPE";
    public static int REQUEST_CAMERA = 3;
    public static int SELECT_FILE = 4;
    public static int AUTO_FILL_DETAIL = 1;
    public static int MANUALLY_FILL_DETAIL = 2;
    private final String Tag = "Util";

    public static int getDeviceWidth() {
//        DisplayMetrics dm = new DisplayMetrics();
        MainActivity.activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels > dm.heightPixels ? dm.heightPixels : dm.widthPixels;
    }

    public static int getDeviceHeight() {
//        DisplayMetrics dm = new DisplayMetrics();
        MainActivity.activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels > dm.heightPixels ? dm.widthPixels : dm.heightPixels;
    }


}
