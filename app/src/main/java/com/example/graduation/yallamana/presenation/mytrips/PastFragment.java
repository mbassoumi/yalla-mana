package com.example.graduation.yallamana.presenation.mytrips;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastFragment extends Fragment {


    RecyclerView recyclerView;
    private List<Trip> trips;
    private TripAdapter tripAdapter;
    private Random random;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_past, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trips  = (List<Trip>) getArguments().getSerializable("pasts");


//
//       trips = new ArrayList<>();
        random = new Random();


        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView)getActivity(). findViewById(R.id.recycler_view6);
      //  final FloatingActionButton mFab =(FloatingActionButton)getActivity().findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tripAdapter = new TripAdapter(recyclerView, trips, getActivity(),"past");
        recyclerView.setAdapter(tripAdapter);



    }




}
