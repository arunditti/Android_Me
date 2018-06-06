package com.arunditti.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        //Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {

            // Create a new BodyPartFragment instance and display it using the FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setListIndex(1);

            //Use a FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //Fragment transaction
            fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();

            BodyPartFragment bodyFragment =  new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
        }
    }
}
