package com.example.ad_exp_sharedata;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ad_exp_sharedata.R;
public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                Intent intent = new Intent(MainActivity.this,
                        com.example.ad_exp_sharedata.SecondActivity.class);
                intent.putExtra("username", name);
                startActivity(intent);
            }
        });
    }
}