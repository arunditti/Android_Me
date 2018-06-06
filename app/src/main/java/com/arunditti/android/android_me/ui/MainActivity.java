package com.arunditti.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

//Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}
