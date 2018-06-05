package com.arunditti.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arunditti.android.android_me.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new BodyPartFragment instance and display it using the FragmentManager
        BodyPartFragment headFragment = new BodyPartFragment();

        //Use a FragmentManager and transaction to add the fragment to the screen
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction
        fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();
    }
}
