package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppListAdapter extends ArrayAdapter<AppInfo> {
    private Context context;
    private List<AppInfo> appsList;

    public AppListAdapter(@NonNull Context context, List<AppInfo> appsList) {
        super(context, R.layout.item_app_list, appsList);
        this.context = context;
        this.appsList = appsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_app_list, parent, false);
        }

        AppInfo currentApp = appsList.get(position);

        ImageView appIcon = convertView.findViewById(R.id.app_icon);
        TextView appName = convertView.findViewById(R.id.app_name);

        appIcon.setImageDrawable(currentApp.getIcon());
        appName.setText(currentApp.getAppName());

        convertView.setOnLongClickListener(v -> {
            showAppOptionsDialog(currentApp);
            return true;
        });

        return convertView;
    }

    private void showAppOptionsDialog(AppInfo appInfo) {
        String[] options = {
                "App Type",
                "Open App",
                "Uninstall",
                "View Details"
        };

        new AlertDialog.Builder(context)
                .setTitle(appInfo.getAppName())
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0: // App Type
                            showAppTypeDialog(appInfo);
                            break;
                        case 1: // Open App
                            openApp(appInfo.getPackageName());
                            break;
                        case 2: // Uninstall
                            uninstallApp(appInfo.getPackageName());
                            break;
                        case 3: // View Details
                            showAppDetails(appInfo);
                            break;
                    }
                })
                .show();
    }

    private void showAppTypeDialog(AppInfo appInfo) {
        String type = appInfo.isSystemApp() ? "System App" : "User-Installed App";
        new AlertDialog.Builder(context)
                .setTitle("App Type")
                .setMessage(type)
                .setPositiveButton("OK", null)
                .show();
    }

    private void openApp(String packageName) {
        Intent launchIntent = context.getPackageManager()
                .getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        } else {
            Toast.makeText(context, "App cannot be launched", Toast.LENGTH_SHORT).show();
        }
    }

    private void uninstallApp(String packageName) {
        new AlertDialog.Builder(context)
                .setTitle("Uninstall App")
                .setMessage("Are you sure you want to uninstall this app?")
                .setPositiveButton("Uninstall", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(Uri.parse("package:" + packageName));
                    context.startActivity(intent);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showAppDetails(AppInfo appInfo) {
        Intent intent = new Intent(context, AppDetailsActivity.class);
        intent.putExtra("PACKAGE_NAME", appInfo.getPackageName());
        context.startActivity(intent);
    }
}