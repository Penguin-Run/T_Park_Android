package com.example.hw1_fragments_with_list;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentCloseUpDigit extends Fragment {
    private static final String DIGIT_KEY = "digit";

    TextView digit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_close_up_digit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = "No";
        Bundle arguments = getArguments();
        if (arguments != null) {
            text = arguments.getString(DIGIT_KEY);
        }
        TextView digit = ((TextView)view.findViewById(R.id.digit));
        digit.setText(text);

        assert text != null;
        int num = Integer.parseInt(text);
        if (num % 2 == 0) digit.setTextColor(Color.RED);
        else digit.setTextColor(Color.BLUE);


    }

    static FragmentCloseUpDigit newInstance(int param) {
        FragmentCloseUpDigit fragment = new FragmentCloseUpDigit();
        Bundle bundle = new Bundle();
        bundle.putString(DIGIT_KEY, String.valueOf(param));
        fragment.setArguments(bundle);
        return fragment;
    }
}
