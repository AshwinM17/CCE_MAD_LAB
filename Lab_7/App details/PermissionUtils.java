package com.example.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    public static List<String> getAppPermissions(Context context, String packageName) {
        List<String> permissions = new ArrayList<>();
        try {
            String[] requestedPermissions = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
                    .requestedPermissions;

            if (requestedPermissions != null) {
                for (String permission : requestedPermissions) {
                    if (isSpecialPermission(permission)) {
                        permissions.add(permission);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("PermissionUtils", "Error getting permissions", e);
        }
        return permissions;
    }

    private static boolean isSpecialPermission(String permission) {
        // List of special permissions to highlight
        String[] specialPermissions = {
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        for (String special : specialPermissions) {
            if (permission.equals(special)) {
                return true;
            }
        }
        return false;
    }
}