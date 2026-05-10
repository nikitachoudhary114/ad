package com.example.colorpicker;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;
public class MainActivity extends AppCompatActivity {
    private ColorPickerView colorPickerView;
    private BrightnessSlideBar brightnessSlide;
    private View colorPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Initialize views
        colorPickerView = findViewById(R.id.colorPickerView);
        brightnessSlide = findViewById(R.id.brightnessSlide);
        colorPreview = findViewById(R.id.colorPreview);
// Attach brightness slider
        colorPickerView.attachBrightnessSlider(brightnessSlide);
// Correct Listener
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                colorPreview.setBackgroundColor(color);
            }
        });
    }
}