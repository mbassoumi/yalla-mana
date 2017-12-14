package com.example.graduation.yallamana.presenation.post.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.post.utils.PostAdapter;
import com.example.graduation.yallamana.presenation.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment {


    RecyclerView recyclerView;
    private List<Post> posts;
    private PostAdapter postAdapter;
    private Random random;
    private Menu menu;
    TextView user_name;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_home, container, false);
//

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        posts = new ArrayList<>();
        random = new Random();

        //set dummy data
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setName("Mais helou ");
            post.setDate("3-12-2017");
            post.setPost("Hi i want t say hi ............................................ bye");
            posts.add(post);
        }
        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postAdapter = new PostAdapter(recyclerView, posts, getActivity());
        recyclerView.setAdapter(postAdapter);

    }


    /*    RecyclerView rv = (RecyclerView)getActivity().findViewById(R.id.rv);
        rv.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
       */
}