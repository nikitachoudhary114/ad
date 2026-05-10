package com.example.myappregistration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    TextView tvRollNo, tvName, tvSubject, tvGender, tvQualification;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tvRollNo        = findViewById(R.id.tv_show_roll);
        tvName          = findViewById(R.id.tv_show_name);
        tvSubject       = findViewById(R.id.tv_show_subject);
        tvGender        = findViewById(R.id.tv_show_gender);
        tvQualification = findViewById(R.id.tv_show_qualification);

        Intent intent = getIntent();
        tvRollNo.setText("Roll No: "        + intent.getStringExtra("rollNo"));
        tvName.setText("Name: "             + intent.getStringExtra("name"));
        tvSubject.setText("Subject: "       + intent.getStringExtra("subject"));
        tvGender.setText("Gender: "         + intent.getStringExtra("gender"));
        tvQualification.setText("Qualification: " + intent.getStringExtra("qualification"));
    }
}