package com.example.myapplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.List;

public class AppDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_details);

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.app_details_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get package name from intent
        String packageName = getIntent().getStringExtra("PACKAGE_NAME");

        // Populate details
        displayAppDetails(packageName);
    }

    private void displayAppDetails(String packageName) {
        PackageManager pm = getPackageManager();

        try {
            // Get package info
            PackageInfo packageInfo = pm.getPackageInfo(packageName,
                    PackageManager.GET_PERMISSIONS | PackageManager.GET_META_DATA);

            // Find views
            TextView nameView = findViewById(R.id.app_name_details);
            TextView versionView = findViewById(R.id.app_version);
            TextView sizeView = findViewById(R.id.app_size);
            TextView permissionsView = findViewById(R.id.app_permissions);

            // Set app name
            nameView.setText(packageInfo.applicationInfo.loadLabel(pm));

            // Set version
            versionView.setText("Version: " + packageInfo.versionName);

            // Calculate and set app size
            File apkFile = new File(packageInfo.applicationInfo.sourceDir);
            long size = apkFile.length();
            sizeView.setText("Size: " + formatFileSize(size));

            // Get and display permissions
            List<String> specialPermissions = PermissionUtils.getAppPermissions(this, packageName);
            if (!specialPermissions.isEmpty()) {
                StringBuilder permissionText = new StringBuilder("Special Permissions:\n");
                for (String permission : specialPermissions) {
                    permissionText.append("• ").append(formatPermissionName(permission)).append("\n");
                }
                permissionsView.setText(permissionText.toString());
            } else {
                permissionsView.setText("No special permissions found.");
            }

        } catch (PackageManager.NameNotFoundException e) {
            // Handle error
            TextView errorView = findViewById(R.id.app_name_details);
            errorView.setText("App details could not be loaded.");
        }
    }

    private String formatFileSize(long size) {
        if (size < 1024) return size + " B";
        int exp = (int) (Math.log(size) / Math.log(1024));
        char pre = "KMGTPE".charAt(exp-1);
        return String.format("%.1f %sB", size / Math.pow(1024, exp), pre);
    }

    private String formatPermissionName(String permission) {
        // Remove standard prefix and format nicely
        return permission.replace("android.permission.", "")
                .replace("_", " ")
                .toLowerCase();
    }
}