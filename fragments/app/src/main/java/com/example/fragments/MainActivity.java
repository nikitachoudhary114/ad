package com.example.fragments;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements
        FirstFragment.OnDataSend {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new com.example.fragments.FirstFragment())
                .commit();
    }
    @Override
    public void sendData(String data) {
        com.example.fragments.SecondFragment secondFragment = new
                com.example.fragments.SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", data);
        secondFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null)
                .commit();
    }
}