package com.example.graduation.yallamana.presenation.mytrips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.graduation.yallamana.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastFragment extends Fragment {


    public PastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_past, container, false);


        return rootView;
    }

}
