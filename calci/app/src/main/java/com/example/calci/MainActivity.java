package com.example.calci;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView display;
    double num1 = 0, num2=0, result= 0;
    String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
    }
    // Number button click
    public void numberClick(View view) {
        Button button = (Button) view;
// Avoid leading zero like 000
        if (display.getText().toString().equals("0")) {
            display.setText(button.getText().toString());
        } else {
            display.append(button.getText().toString());
        }
    }
    // Operator button click
    public void operatorClick(View view) {
        Button button = (Button) view;
        if (display.getText().toString().isEmpty()) return;
        num1=Double.parseDouble(display.getText().toString());
        operator = button.getText().toString();
        display.setText(""); // clear for next number
    }
    // Equals button click
    public void equalsClick(View view) {
        if (display.getText().toString().isEmpty()) return;
        num2=Double.parseDouble(display.getText().toString());
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1- num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    display.setText("Error");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                display.setText("Error");
                return;
        }
        display.setText(String.valueOf(result));
    }
    // Clear button
    public void clearClick(View view) {
        display.setText("0"); // better UX than empty
        num1=num2=result =0;
        operator = "";
    }
}