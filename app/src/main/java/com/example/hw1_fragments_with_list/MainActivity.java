package com.example.hw1_fragments_with_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
/*
Issues:
1. Data don't get restored when back button is pressed (android button)

 */

public class MainActivity extends AppCompatActivity implements ActivityAccess {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding main fragment on the screen
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fr_place, new FragmentMain())
                    .commit();
        }

    }

    @Override
    public void onItemClick(int digit) {
        // replacing fragment
        Fragment fragment = FragmentCloseUpDigit.newInstance(digit);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fr_place, fragment)
                .addToBackStack("Fragment close-up digit")
                .commit();

    }

}
