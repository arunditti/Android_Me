package com.arunditti.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

//Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    //Variables to store the values of the list index of the selected images. Default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position Clicked: " + position, Toast.LENGTH_SHORT).show();

        //Based on where the user has clicked, store the selected list index for the head, body and leg
        //bodyPartNumber will be  = 0 for the head fragment, 1 for the body and 2 for the leg fragment
        //Dividing by 12 gives us these integer values because each list of image resources has a size of 12
        int bodyPartnumber = position /12;

        //Store the correct list index no matter where in the image list has been clicked
        //This ensures that t5he index will always be a value between 0-11
        int listIndex = position -12*bodyPartnumber;

        //Set the currently displaayed item fort he correct body part fragment
        switch (bodyPartnumber) {
            case 0:
                headIndex = listIndex;
                break;
            case 1:
                bodyIndex = listIndex;
                break;
            case 2:
                legIndex = listIndex;
                break;
        }

        //Put this information in a bundle and attach it to the intent taht will launch the AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        //Attach the bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        //Get a reference to the next button and launch the intent when the button is clicked
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
