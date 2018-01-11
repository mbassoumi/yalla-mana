package com.example.graduation.yallamana.presenation.post.utils;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.Comment;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;
    private Activity activity;
    private List<Comment> comments;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public CommentAdapter(RecyclerView recyclerView, List<Comment> comments, Activity activity) {
        this.comments = comments;
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
        return comments.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.comment_card, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
           Comment comment = comments.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.name.setText(comment.getUserID().getName());
          userViewHolder.comment.setText(comment.getBody());

        }
    }

    @Override
    public int getItemCount() {
        if (comments==null){
            return 0;

        }
        return comments.size();
    }




    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public MultiAutoCompleteTextView comment;
        public ImageView comentedimage;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_name);
          comment=(MultiAutoCompleteTextView) view.findViewById(R.id.comment_area);
          comentedimage=(ImageView)view.findViewById(R.id.imageView3);
        }
    }
}