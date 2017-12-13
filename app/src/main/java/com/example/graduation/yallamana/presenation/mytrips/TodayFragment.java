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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Trips> trips;
    private TripAdapter tripAdapter;
    private Random random;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_today, container, false);
//        final CollapsingToolbarLayout toolbarC = (CollapsingToolbarLayout)getActivity().findViewById(R.id.collapsing_toolbar);
//
//        toolbarC.setVisibility(View.GONE);
//
//        AppBarLayout appbar = (AppBarLayout)getActivity(). findViewById(R.id.appbar);
//        Toolbar toolbar= (Toolbar)getActivity(). findViewById(R.id.toolbar);
//        ImageView image = (ImageView) getActivity().findViewById(R.id.backdrop);
//        TextView text =(TextView)getActivity().findViewById(R.id.user_name);
//        appbar.setVisibility(View.GONE);
//        toolbar.setVisibility(View.GONE);
//        appbar.setVisibility(View.GONE);
//        appbar.setExpanded(false);
//
//        text.setVisibility(View.GONE);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
//
//        toolbar.setTitle("All contents");
//        // Person p = new Person("Mais","21",1);

        trips = new ArrayList<>();
        random = new Random();

        //set dummy data
        for (int i = 0; i < 10; i++) {
            Trips trip = new Trips();
            trip.setFrom("Ramallah");
            trip.setDate("3-12-2017");
            trip.setTo("Nablus");
            trips.add(trip);
        }
        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView)getActivity(). findViewById(R.id.recycler_view4);
        final FloatingActionButton mFab =(FloatingActionButton)getActivity().findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tripAdapter = new TripAdapter(recyclerView, trips, getActivity());
        recyclerView.setAdapter(tripAdapter);



    }




}
