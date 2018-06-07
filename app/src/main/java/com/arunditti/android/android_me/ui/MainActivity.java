package com.arunditti.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

//Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    //Variables to store the values of the list index of the selected images. Default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //Create a variable to track whether to display two-pane or single-pane mode
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Determine if you are using two-pane or single-pane display
        if(findViewById(R.id.android_me_linear_layout) != null) {
            //This linearLayout will only exist in the two-pane tablet case
            mTwoPane = true;

            //Getting rid of the Next button that appears on phone
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            //Change the gridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //Create new body part fragment
            //Only create new fragments when there is no previously saved state
            if(savedInstanceState == null) {

                // Create a new BodyPartFragment instance and display it using the FragmentManager
                BodyPartFragment headFragment = new BodyPartFragment();
                // Set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());

                //Get the correct index to access in the array of head images from the intent
                //Set the default value to 0
                int headIndex = getIntent().getIntExtra("headIndex", 0);
                headFragment.setListIndex(headIndex);

                //Use a FragmentManager and transaction to add the fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                //Fragment transaction
                fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();

                BodyPartFragment bodyFragment =  new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
                bodyFragment.setListIndex(bodyIndex);
                fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                int legIndex = getIntent().getIntExtra("legIndex", 0);
                legFragment.setListIndex(legIndex);
                fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
            }

        } else {
            mTwoPane = false;
        }

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

        if(mTwoPane) {
            //Handle two pane case
            BodyPartFragment newFragment = new BodyPartFragment();

            //Set the currently displaayed item fort he correct body part fragment
            switch (bodyPartnumber) {
                case 0:
                    //A head image is clicked. Give the correct image resources to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    //Replace old head fragemnt with new one
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, newFragment).commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {

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
}
