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
import java.util.zip.Inflater;


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


        AppBarLayout mAppBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle(user_name.getText().toString());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    showOption(R.id.action_add);


                    showOption(R.id.action_add);
                } else if (isShow) {
                    isShow = false;
                    collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle("");

                    hideOption(R.id.action_add);
                }
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.post_toolbar, menu);
        hideOption(R.id.action_add);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    /*    RecyclerView rv = (RecyclerView)getActivity().findViewById(R.id.rv);
        rv.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
       */
}