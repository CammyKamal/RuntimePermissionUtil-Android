package com.runtimeutils.runtimepermissionutilexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import constants.Permission;
import interfaces.PermissionCallback;
import permission.RuntimePermissions;

public class MainActivity extends AppCompatActivity {

    RuntimePermissions runtimePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runtimePermissions = new RuntimePermissions(MainActivity.this, Permission.CALL_PHONE_PERMISSION, new PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Granted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_LONG).show();
            }
        });
        runtimePermissions.askUserPermissionGranted();
    }

    /**
     * need for Android 6+ real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        runtimePermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
