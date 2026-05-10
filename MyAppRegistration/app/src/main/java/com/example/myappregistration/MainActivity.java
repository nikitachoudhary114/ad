package com.example.myappregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] subjects = {"Mobile Computing", "Android Development", "Digital Marketing", "Fibre Optics"};

    Spinner spinnerSubject;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;
    CheckBox checkBoxSSC, checkBoxHSC, checkBoxBachelor, checkBoxMaster;
    EditText editRollNo, editName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        spinnerSubject  = findViewById(R.id.spinner);
        radioGroup      = findViewById(R.id.radiogroup);
        radioMale       = findViewById(R.id.radiobuttonm);
        radioFemale     = findViewById(R.id.radiobuttonf);
        checkBoxSSC     = findViewById(R.id.checkboxssc);
        checkBoxHSC     = findViewById(R.id.checkboxhsc);
        checkBoxBachelor= findViewById(R.id.checkboxbachelor);
        checkBoxMaster  = findViewById(R.id.checkboxmaster);
        editRollNo      = findViewById(R.id.edit_roll_no);
        editName        = findViewById(R.id.edit_name);
        btnSubmit       = findViewById(R.id.btn_submit);

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Submit Button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rollNo = editRollNo.getText().toString().trim();
                String name   = editName.getText().toString().trim();

                // Validation
                if (rollNo.isEmpty()) {
                    editRollNo.setError("Please enter Roll No");
                    return;
                }
                if (name.isEmpty()) {
                    editName.setError("Please enter Name");
                    return;
                }
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!checkBoxSSC.isChecked() && !checkBoxHSC.isChecked()
                        && !checkBoxBachelor.isChecked() && !checkBoxMaster.isChecked()) {
                    Toast.makeText(MainActivity.this, "Please select at least one Qualification", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Collect data
                String subject = spinnerSubject.getSelectedItem().toString();
                String gender  = radioMale.isChecked() ? "Male" : "Female";

                StringBuilder qualification = new StringBuilder();
                if (checkBoxSSC.isChecked())      qualification.append("SSC  ");
                if (checkBoxHSC.isChecked())      qualification.append("HSC  ");
                if (checkBoxBachelor.isChecked()) qualification.append("Bachelor  ");
                if (checkBoxMaster.isChecked())   qualification.append("Master");

                // Pass data to ShowActivity
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("rollNo",        rollNo);
                intent.putExtra("name",          name);
                intent.putExtra("subject",       subject);
                intent.putExtra("gender",        gender);
                intent.putExtra("qualification", qualification.toString().trim());
                startActivity(intent);
            }
        });
    }
}