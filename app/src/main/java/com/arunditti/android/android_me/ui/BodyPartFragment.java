package com.arunditti.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

/**
 * Created by arunditti on 6/5/18.
 */

public class BodyPartFragment extends Fragment {


    //Mandatory Constructor for initiating the fragment
    public BodyPartFragment() {

    }

    //Inflates the fragment layout and sets any image resource
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceSate) {

        //Inflate the AndroidMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the ImageView in the fragment layout
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        //Set the image resource to display
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        //Return root view
        return rootView;

    }
}