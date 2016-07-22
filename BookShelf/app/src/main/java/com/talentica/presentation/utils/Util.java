package com.talentica.presentation.utils;

import android.util.DisplayMetrics;

import com.talentica.presentation.leadCapturePage.base.view.MainActivity;

public class Util {

    public static DisplayMetrics dm = new DisplayMetrics();
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
