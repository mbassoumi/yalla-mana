package com.example.graduation.yallamana.presenation.post.utils;

import android.app.Activity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;


import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.post.Post;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;
    private Activity activity;
    private List<Post> posts;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public PostAdapter(RecyclerView recyclerView, List<Post> posts, Activity activity) {
        this.posts = posts;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return posts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.post_row, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
           Post post = posts.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.name.setText(post.getName());
            userViewHolder.post.setText(post.getpost());
            userViewHolder.date.setText(post.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }




    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView post;
        public TextView date;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_name);
           date = (TextView) view.findViewById(R.id.txt_date);
            post = (TextView) view.findViewById(R.id.post_area);
        }
    }
}