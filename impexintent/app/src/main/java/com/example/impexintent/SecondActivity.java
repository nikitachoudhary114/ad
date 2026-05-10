package com.example.impexintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Simple display for demonstration

        TextView textView = findViewById(R.id.textView);

        textView.setText("Welcome to Second Activity! This was opened via Explicit Intent.");
        // Button for Explicit Intent: Starts SecondActivity

        Button explicitButton = findViewById(R.id.btn2);

        explicitButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // Explicit Intent: Starts a specific activity in the same app

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);

                startActivity(intent);

            }

        });
    }
}