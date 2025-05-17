package com.example.myapplication;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView appListView;
    private AppListAdapter adapter;
    private List<AppInfo> appsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ListView
        appListView = findViewById(R.id.app_list_view);
        loadInstalledApps();
    }

    private void loadInstalledApps() {
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            // Skip system apps if needed
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }

            String appName = packageInfo.loadLabel(pm).toString();
            Drawable icon = packageInfo.loadIcon(pm);
            boolean isSystemApp = (packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;

            // Get app version
            String version;
            try {
                version = pm.getPackageInfo(packageInfo.packageName, 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                version = "Unknown";
            }

            // Get app size
            long size = new File(packageInfo.sourceDir).length();

            AppInfo appInfo = new AppInfo(
                    packageInfo.packageName,
                    appName,
                    icon,
                    isSystemApp,
                    version,
                    size
            );
            appsList.add(appInfo);
        }

        // Sort apps alphabetically
        appsList.sort((a1, a2) -> a1.getAppName().compareToIgnoreCase(a2.getAppName()));

        // Set up adapter
        adapter = new AppListAdapter(this, appsList);
        appListView.setAdapter(adapter);
    }
}