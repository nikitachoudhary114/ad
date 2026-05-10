package com.example.fragments;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.fragments.R;
public class FirstFragment extends Fragment {
    EditText editText;
    Button button;
    OnDataSend dataSend;
    public interface OnDataSend {
        void sendData(String data);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataSend = (OnDataSend) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(v-> {
            String data = editText.getText().toString();
            if (!TextUtils.isEmpty(data)) {
                dataSend.sendData(data);
            }
        });
        return view;
    }
}
