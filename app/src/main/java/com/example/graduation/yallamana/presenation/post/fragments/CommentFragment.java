package com.example.graduation.yallamana.presenation.post.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.post.utils.CommentAdapter;
import com.example.graduation.yallamana.util.network.api.Comment;
import com.example.graduation.yallamana.util.network.api.Post;
import com.example.graduation.yallamana.util.network.api.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends android.support.v4.app.Fragment {


    RecyclerView recyclerView;
    private List<Comment> comments;
    private CommentAdapter commentAdapter;
    private Random random;
    private Menu menu;
    TextView user_name;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_comment, container, false);
//

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        comments  = (List<Comment>) getArguments().getSerializable("pasts");

        random = new Random();

        //set dummy data
//        for (int i = 0; i < 10; i++) {
//           Comment comment = new Comment();
//            comment.setUserName("Mais helou ");
//            comment.setComment("Hi i want t say hi ............................................ bye");
//            comments.add(comment);
//        }
        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.command_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       commentAdapter = new CommentAdapter(recyclerView, comments, getActivity());
        recyclerView.setAdapter(commentAdapter);


    }

    /*    RecyclerView rv = (RecyclerView)getActivity().findViewById(R.id.rv);
        rv.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
       */
}