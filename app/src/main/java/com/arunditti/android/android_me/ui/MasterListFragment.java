package com.arunditti.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.arunditti.android.android_me.R;
import com.arunditti.android.android_me.data.AndroidImageAssets;

/**
 * Created by arunditti on 6/5/18.
 */

public class MasterListFragment extends Fragment {

    //Interface that triggers a callback in the host activity
    OnImageClickListener mCallBack;

    //OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the container acticity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //This makes sure that the host activity has implemented the callback interface. If not, it throws an exception
        try {
            mCallBack = (OnImageClickListener) context;
        } catch (ClassCastException e ) {
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }


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

        // Set a clickListener on the GridView and trigger the callback OnImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Trigger the callback method and pass in the position that was clicked
                mCallBack.onImageSelected(position);
            }
        });
        //Return the rootView
        return rootView;
    }
}

