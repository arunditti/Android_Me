package com.arunditti.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

/**
 * Created by arunditti on 6/5/18.
 */

public class MasterListFragment extends Fragment {

    //Mandatory empty constructor
    public MasterListFragment() {
    }

    //Inflate gridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //Create the adapter. This adapter takes in the context and an ArrayList of all the image resources to display
        MasterListAdapter mMasterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //Set the adapter on the GridView
        gridView.setAdapter(mMasterListAdapter);

        //Return the rootView
        return rootView;
    }
}

