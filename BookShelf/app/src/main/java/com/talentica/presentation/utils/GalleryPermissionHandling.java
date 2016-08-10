package com.talentica.presentation.utils;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.talentica.R;

public class GalleryPermissionHandling {
    private static final int MY_PERMISSIONS_REQUEST_GALLERY = 2;
    private static String TAG = "GalleryPermHandling";
    private static String[] PERMISSIONS_GALLERY = {Manifest.permission.READ_EXTERNAL_STORAGE,};
    private static AppCompatActivity parentActivity;
    private static Snackbar snackbar;
    private static TextView randomView;


    public static void handleGalleryPermission(AppCompatActivity activity, TextView anyView) {
        Log.d(TAG, "GalleryPermissionHandling  ");

        parentActivity = activity;
        randomView = anyView;
        if (ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ) {
            requestGalleryPermissions(randomView);
        } else {
            //TODO some method
            launchGallery();
        }

    }

    private static void requestGalleryPermissions(View randomView) {
        Log.d(TAG, "requestGalleryPermissions  ");

        if (ActivityCompat.shouldShowRequestPermissionRationale(parentActivity,
                Manifest.permission.CAMERA)) {
            Log.d(TAG, "shouldShowRequestPermissionRationale  ");
            snackbar = Snackbar.make(randomView, R.string.permission_gallery_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.decide, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(parentActivity, PERMISSIONS_GALLERY,
                                            MY_PERMISSIONS_REQUEST_GALLERY);
                        }
                    });

            CustomSnackbar.show(snackbar, false);
        } else {
            Log.d(TAG, "requestPermissions  ");
            ActivityCompat.requestPermissions(parentActivity, PERMISSIONS_GALLERY,
                    MY_PERMISSIONS_REQUEST_GALLERY);
        }
    }

    private static void launchGallery() {

        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);//
            parentActivity.startActivityForResult(Intent.createChooser(intent, "Select File"), Util.SELECT_FILE);

            //start the scanning activity from the com.google.zxing.client.android.SCAN intent

        } catch (ActivityNotFoundException anfe) {
            snackbar = Snackbar.make(randomView, "No Gallery found", Snackbar.LENGTH_SHORT);
            CustomSnackbar.show(snackbar, true);
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == MY_PERMISSIONS_REQUEST_GALLERY) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                snackbar = Snackbar.make(randomView, R.string.permission_granted, Snackbar.LENGTH_SHORT);
                CustomSnackbar.show(snackbar, false);
                launchGallery();

            } else {
                snackbar = Snackbar.make(randomView, R.string.permission_not_granted, Snackbar.LENGTH_SHORT);
                CustomSnackbar.show(snackbar, true);

            }
        }
    }
}
