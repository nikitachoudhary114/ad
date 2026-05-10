package com.example.fragments;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.fragments.R;
public class SecondFragment extends Fragment {
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        textView = view.findViewById(R.id.textView);
        if (getArguments() != null) {
            String data = getArguments().getString("key");
            textView.setText(data);
        }
        return view;
    }
}