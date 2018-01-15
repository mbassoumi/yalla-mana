package com.example.graduation.yallamana.presenation.post.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.post.fragments.CommentFragment;
import com.example.graduation.yallamana.util.network.api.Comment;
import com.example.graduation.yallamana.util.network.api.Comment1;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Post;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    AppCompatActivity myActivity;
    private boolean isLoading;
    private Activity activity;
    private List<Post> posts;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    SharedPreferences sharedPreferences;

    public PostAdapter(RecyclerView recyclerView, List<Post> posts, Activity activity, SharedPreferences sharedPreferences) {
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
            //  String namee=sharedPreferences.getString("name", "noValue");
            userViewHolder.name.setText(post.getUser().getName());
            userViewHolder.post.setText(post.getBody());
            userViewHolder.date.setText(post.getTime().getDate() + " " + post.getTime().getTime());
            userViewHolder.commentsSend.setEnabled(false);
            if (post.getComments() == null) {
                userViewHolder.commentNumber.setText(" ");
            } else {
                int comment = (post.getComments().size());

                if (comment == 0) {
                    userViewHolder.commentNumber.setText(" ");
                } else {
                    userViewHolder.commentNumber.setText(comment + "");
                }
            }
            userViewHolder.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity = (AppCompatActivity) v.getContext();
                    userViewHolder.comments.setImageDrawable(myActivity.getResources().getDrawable(R.drawable.ic_comment_green_24dp));
//
//                    Intent intent = new Intent(myActivity,Trip_information.class);
//                    intent.putExtra("tripId",trip.getId());
//                    intent.putExtra("tripsStatus",status);
//
//                    myActivity.startActivity(intent);
                    CommentFragment Comment = new CommentFragment();
                    Bundle b2 = new Bundle();
                 List< Comment> comment =post.getComments();
                    b2.putParcelableArrayList("comment", (ArrayList<? extends Parcelable>) comment );
                    Comment.setArguments(b2);
                    FragmentManager fragmentManager = myActivity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_content, Comment);
                    fragmentTransaction.commit();

                }
            });
            userViewHolder.addComment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence str, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence str, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable str) {
                    if (!userViewHolder.addComment.getText().toString().isEmpty()) {
                        userViewHolder.commentsSend.setEnabled(true)
                        ;
                        userViewHolder.commentsSend.setImageDrawable(myActivity.getResources().getDrawable(R.drawable.ic_send_green_24dp));
                    } else {
                        userViewHolder.commentsSend.setEnabled(false)
                        ;
                        userViewHolder.commentsSend.setImageDrawable(myActivity.getResources().getDrawable(R.drawable.ic_send_black_24dp));
                    }

                }
            });


            userViewHolder.commentsSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sharedPreferences = myActivity.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    String token = "Bearer " + sharedPreferences.getString("token", "noValue");
                  RetrofitInterface  retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
                    Comment1 comment = new Comment1();
                    comment.setBody(userViewHolder.addComment.getText().toString());
                    comment.setPostID(post.getId());
                    comment.setBody(userViewHolder.addComment.getText().toString());

                    Call<Example> call2 = retrofitInterface.addNewComment(token, comment);
                    call2.enqueue(new Callback<Example>() {


                        @Override
                        public void onResponse(Call<Example> call, Response<Example> response) {
                            AppCompatActivity myActivity = (AppCompatActivity) v.getContext();

                            if (response.code()==200){
    Toast.makeText(myActivity, "You comment added ", Toast.LENGTH_LONG).show();
    userViewHolder.addComment.setText("");

                        }
                        else {
    Toast.makeText(myActivity, "You comment not  added ", Toast.LENGTH_LONG).show();

}
                        }

                        @Override
                        public void onFailure(Call<Example> call, Throwable t) {

                        }
                    });

                }
            });


            userViewHolder.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity = (AppCompatActivity) v.getContext();

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (posts == null) {
            return 0;
        }
        return posts.size();
    }


    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView post;
        public TextView date, addComment;
        public TextView commentNumber;
        public ImageView comments;
        public ImageView commentsSend;
        public ImageView close;

        public UserViewHolder(View view) {
            super(view);
            myActivity = (AppCompatActivity) view.getContext();

            name = (TextView) view.findViewById(R.id.txt_name);
            date = (TextView) view.findViewById(R.id.txt_date);
            post = (TextView) view.findViewById(R.id.post_area);
            addComment = (TextView) view.findViewById(R.id.addComment);
            commentNumber = (TextView) view.findViewById(R.id.commentNum);
            comments = (ImageView) view.findViewById(R.id.comment_btn);
            commentsSend = (ImageView) view.findViewById(R.id.commentSend);
            close = (ImageView) view.findViewById(R.id.close);
        }
    }
}