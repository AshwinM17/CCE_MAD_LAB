package com.example.multiplebuttons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button references
        Button buttonCupcake = findViewById(R.id.button_cupcake);
        Button buttonDonut = findViewById(R.id.button_donut);
        Button buttonEclair = findViewById(R.id.button_eclair);
        Button buttonFroYo = findViewById(R.id.button_froYo);
        Button buttonGingerbread = findViewById(R.id.button_gingerbread);
        Button buttonHoneycomb = findViewById(R.id.button_honeycomb);
        Button buttonIcs = findViewById(R.id.button_ics);
        Button buttonJellybean = findViewById(R.id.button_jellybean);
        Button buttonKitkat = findViewById(R.id.button_kitkat);
        Button buttonLollipop = findViewById(R.id.button_lollipop);
        Button buttonMarshmallow = findViewById(R.id.button_marshmallow);
        Button buttonNougat = findViewById(R.id.button_nougat);
        Button buttonOreo = findViewById(R.id.button_oreo);
        Button buttonPie = findViewById(R.id.button_pie);
        Button buttonAndroid10 = findViewById(R.id.button_android10);
        Button buttonAndroid11 = findViewById(R.id.button_android11);
        Button buttonAndroid12 = findViewById(R.id.button_android12);
        Button buttonAndroid13 = findViewById(R.id.button_android13);
        Button buttonAndroid14 = findViewById(R.id.button_android14);

        // Set listeners for each button to show a Toast with the Android version
        buttonCupcake.setOnClickListener(v -> showToast("Cupcake (1.5)", R.drawable.cupcake_icon));
        buttonDonut.setOnClickListener(v -> showToast("Donut (1.6)", R.drawable.donut_icon));
        buttonEclair.setOnClickListener(v -> showToast("Eclair (2.0)", R.drawable.eclair_icon));
        buttonFroYo.setOnClickListener(v -> showToast("FroYo (2.2)", R.drawable.froyo_icon));
        buttonGingerbread.setOnClickListener(v -> showToast("Gingerbread (2.3)", R.drawable.gingerbread_icon));
        buttonHoneycomb.setOnClickListener(v -> showToast("Honeycomb (3.0)", R.drawable.honeycomb_icon));
        buttonIcs.setOnClickListener(v -> showToast("Ice Cream Sandwich (4.0)", R.drawable.ics_icon));
        buttonJellybean.setOnClickListener(v -> showToast("Jellybean (4.1)", R.drawable.jellybean_icon));
        buttonKitkat.setOnClickListener(v -> showToast("KitKat (4.4)", R.drawable.kitkat_icon));
        buttonLollipop.setOnClickListener(v -> showToast("Lollipop (5.0)", R.drawable.lollipop_icon));
        buttonMarshmallow.setOnClickListener(v -> showToast("Marshmallow (6.0)", R.drawable.marshmallow_icon));
        buttonNougat.setOnClickListener(v -> showToast("Nougat (7.0)", R.drawable.nougat_icon));
        buttonOreo.setOnClickListener(v -> showToast("Oreo (8.0)", R.drawable.oreo_icon));
        buttonPie.setOnClickListener(v -> showToast("Pie (9.0)", R.drawable.pie_icon));
        buttonAndroid10.setOnClickListener(v -> showToast("Android 10", R.drawable.android10_icon));
        buttonAndroid11.setOnClickListener(v -> showToast("Android 11", R.drawable.android11_icon));
        buttonAndroid12.setOnClickListener(v -> showToast("Android 12", R.drawable.android12_icon));
        buttonAndroid13.setOnClickListener(v -> showToast("Android 13", R.drawable.android13_icon));
        buttonAndroid14.setOnClickListener(v -> showToast("Android 14", R.drawable.android14_icon));
    }

    // Method to show the Toast message with custom layout
    private void showToast(String message, int iconResId) {
        // Inflate the custom Toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Set the image and message dynamically
        ImageView imageView = layout.findViewById(R.id.toast_image); // ID must match the one in XML
        imageView.setImageResource(iconResId); // Set the icon resource
        TextView textView = layout.findViewById(R.id.toast_message); // ID must match the one in XML
        textView.setText(message); // Set the message text

        // Create and show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout); // Set the custom layout as the view
        toast.show();
    }
}