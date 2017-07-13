/**
 * Copyright (c) 2017-present, CammyKamal, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this library in source code or binary form for use
 * in connection with the handling runtime permission in android by CammyKamal.
 */

package permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import interfaces.PermissionCallback;



public class RuntimePermissions {

    private Activity activity;
    private Fragment fragment;
    private final static int PERMISSION_REQUEST_CODE = 12;
    private PermissionCallback permissionCallback;
    private int permission;

    /**
     *  Constructor to initialize
     * @param activity : Context of the activity in which permission is to be implemented
     * @param permission : permission to be check for run time
     * @param permissionCallback : abstract class object for getting callback methods for success and failure of permission
     */
    public RuntimePermissions(Activity activity, int permission , PermissionCallback permissionCallback) {
        this.activity = activity;
        this.permission=permission;
        this.permissionCallback = permissionCallback;
    }

    /**
     *  Constructor to initialize
     * @param activity : Context of the activity in which permission is to be implemented
     * @param permission : permission to be check for run time
     * @param fragment : fragment object in which permission is to be implemented
     * @param permissionCallback : abstract class object for getting callback methods for success and failure of permission
     */
    public RuntimePermissions(Activity activity, int permission, Fragment fragment, PermissionCallback permissionCallback) {
        this(activity, permission,permissionCallback);
        this.fragment = fragment;
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                permissionCallback.onPermissionGranted();
            }else{
                permissionCallback.onPermissionDenied();
            }
        }
    }

    /**
     *  Method which ask permission from user
     */
    public void askUserPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            String mPermission=getPermissionValue(permission);
            if (ContextCompat.checkSelfPermission(activity, mPermission)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(activity,mPermission)
                            == PackageManager.PERMISSION_GRANTED) {
                permissionCallback.onPermissionGranted();
            } else {
                if (fragment == null) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{mPermission,
                                    mPermission}, PERMISSION_REQUEST_CODE);
                } else {
                    fragment.requestPermissions(
                            new String[]{mPermission,
                                    mPermission}, PERMISSION_REQUEST_CODE);
                }
            }
        } else {
            permissionCallback.onPermissionGranted();
        }
    }

    /**
     *
     * @param permission : permission value in integer
     * @return string permission for manifest check
     */
    private String getPermissionValue(int permission){
        switch (permission){
            case 0:
                return Manifest.permission.READ_SMS;
            case 1:
                return Manifest.permission.WRITE_EXTERNAL_STORAGE;
            case 2:
                return Manifest.permission.READ_CONTACTS;
            case 3:
                return Manifest.permission.WRITE_CALENDAR;
            case 4:
                return Manifest.permission.CAMERA;
            case 5:
                return Manifest.permission.CALL_PHONE;
            case 6:
                return Manifest.permission.RECORD_AUDIO;
            case 7:
                return Manifest.permission.ACCESS_FINE_LOCATION;
            case 8:
                return Manifest.permission.ACCESS_COARSE_LOCATION;
            case 9:
                return Manifest.permission.SEND_SMS;
            default:
                return null;
        }
    }

}
