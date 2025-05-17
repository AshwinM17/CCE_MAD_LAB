package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String packageName;
    private String appName;
    private Drawable icon;
    private boolean isSystemApp;
    private String version;
    private long size;

    public AppInfo(String packageName, String appName, Drawable icon,
                   boolean isSystemApp, String version, long size) {
        this.packageName = packageName;
        this.appName = appName;
        this.icon = icon;
        this.isSystemApp = isSystemApp;
        this.version = version;
        this.size = size;
    }

    // Getters and setters
    public String getPackageName() { return packageName; }
    public String getAppName() { return appName; }
    public Drawable getIcon() { return icon; }
    public boolean isSystemApp() { return isSystemApp; }
    public String getVersion() { return version; }
    public long getSize() { return size; }
}