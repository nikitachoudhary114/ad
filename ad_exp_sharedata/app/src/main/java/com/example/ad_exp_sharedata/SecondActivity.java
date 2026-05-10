package com.example.ad_exp_sharedata;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class SecondActivity extends AppCompatActivity {
    TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewResult = findViewById(R.id.textViewResult);
        String receivedName = getIntent().getStringExtra("username");
        textViewResult.setText("Hello, " + receivedName);
    }
}