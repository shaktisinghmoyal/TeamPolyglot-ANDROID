package com.talentica.presentation.utils;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.talentica.R;

public class CameraPermissionHandling {


    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private static String TAG = "cameraPermHandling";
    private static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA,};
    private static AppCompatActivity parentActivity;
    private static Snackbar snackbar;
    private static TextView randomView;


    public static void handleCameraPermission(AppCompatActivity activity, TextView anyView) {
        Log.d(TAG, "handleCameraPermission  ");

        parentActivity = activity;
        randomView = anyView;
        if (ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                ) {
            requestCameraPermissions(randomView);
        } else {
            //TODO some method
            launchCamera();
        }

    }

    private static void requestCameraPermissions(View randomView) {
        Log.d(TAG, "requestCameraPermissions  ");

        if (ActivityCompat.shouldShowRequestPermissionRationale(parentActivity,
                Manifest.permission.CAMERA)) {
            Log.d(TAG, "shouldShowRequestPermissionRationale  ");
            snackbar = Snackbar.make(randomView, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.decide, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(parentActivity, PERMISSIONS_CAMERA,
                                            MY_PERMISSIONS_REQUEST_CAMERA);
                        }
                    });

            CustomSnackbar.show(snackbar, false);
        } else {
            Log.d(TAG, "requestPermissions  ");
            ActivityCompat.requestPermissions(parentActivity, PERMISSIONS_CAMERA,
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    private static void launchCamera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            parentActivity.startActivityForResult(intent, Util.REQUEST_CAMERA);
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent

        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog

            snackbar = Snackbar.make(randomView, "No Camera found", Snackbar.LENGTH_SHORT);
            CustomSnackbar.show(snackbar, true);
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {


            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                snackbar = Snackbar.make(randomView, R.string.permission_granted, Snackbar.LENGTH_SHORT);
                CustomSnackbar.show(snackbar, false);
                launchCamera();

            } else {
                snackbar = Snackbar.make(randomView, R.string.permission_not_granted, Snackbar.LENGTH_SHORT);
                CustomSnackbar.show(snackbar, true);

            }
        }

    }
}
