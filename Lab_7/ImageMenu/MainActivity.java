package com.example.imagemenu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView displayImageView;

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

        // Find the menu icon and display image view
        ImageView menuIcon = findViewById(R.id.menu_icon);
        displayImageView = findViewById(R.id.display_image);

        // Set click listener for menu icon
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create popup menu
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.image_menu, popupMenu.getMenu());

                // Set menu item click listener
                popupMenu.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();

                    if (itemId == R.id.image_1) {
                        // Display Image-1
                        displayImageView.setImageResource(R.drawable.image_1);
                        Toast.makeText(MainActivity.this, "Image-1 Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    } else if (itemId == R.id.image_2) {
                        // Display Image-2
                        displayImageView.setImageResource(R.drawable.image_2);
                        Toast.makeText(MainActivity.this, "Image-2 Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    return false;
                });

                // Show the popup menu
                popupMenu.show();
            }
        });
    }
}