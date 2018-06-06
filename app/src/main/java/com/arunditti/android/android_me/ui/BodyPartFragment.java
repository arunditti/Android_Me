package com.arunditti.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arunditti on 6/5/18.
 */

public class BodyPartFragment extends Fragment {

    private static final String LOG_TAG = BodyPartFragment.class.getSimpleName();

    //Final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_id";
    public static final String LIST_INDEX = "list_index";

    //Variables to store a list of image resources and the index of the image that this fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    //Mandatory Constructor for initiating the fragment
    public BodyPartFragment() {

    }

    //Inflates the fragment layout and sets any image resource
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceSate) {

        //Load the save state the lsit of images and list index if there is one
        if(saveInstanceSate != null) {
            mImageIds = saveInstanceSate.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = saveInstanceSate.getInt(LIST_INDEX);
        }

        //Inflate the AndroidMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        //If the list of imageIds exist, set the image resource to the correct item in that list
        //Otherwise create a Log statement that indicates that the list was npt found
        if(mImageIds != null) {
            //Set the image resource to display
            imageView.setImageResource(mImageIds.get(mListIndex));

            //Set a clickListener on the image view
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListIndex < mImageIds.size()-1) {
                        mListIndex++;
                    } else {
                        //The end of the list has been reached,so return to begining index
                        mListIndex = 0;
                    }

                    //Set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });

        } else {
            Log.v(LOG_TAG, "This fragment has null list of image id's");
        }


        //Return root view
        return rootView;
    }

    //Setter method to keep track of the list images this fragment can display and which image in the list is currently being displayed
    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setListIndex(int index) {
        mListIndex = index;
    }

    //Save the corrent state of this fragment
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}