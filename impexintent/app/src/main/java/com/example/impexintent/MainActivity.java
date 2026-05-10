package com.example.impexintent;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        // Button for Implicit Intent: Opens a website

        Button implicitButton = findViewById(R.id.implicitButton);

        implicitButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // Implicit Intent: Action to view a URL, handled by any browser app

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://mescoe.mespune.org/"));

                startActivity(intent);

            }

        });


        // Button for Explicit Intent: Starts SecondActivity

        Button explicitButton = findViewById(R.id.explicitButton);

        explicitButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // Explicit Intent: Starts a specific activity in the same app

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);

            }

        });
    }
}