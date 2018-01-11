package com.example.graduation.yallamana.presenation.post.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.Comment1;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {
    EditText post_area;
    Button okPost;
    Button canclePost;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_posts, container, false);
//

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  comments  = (List<Comment>) getArguments().getSerializable("pasts");

        post_area = (EditText) view.findViewById(R.id.post_area);
        okPost = (Button) view.findViewById(R.id.post_ok);
        canclePost = (Button) view.findViewById(R.id.post_cancel);
        post_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable str) {
                if (!post_area.getText().toString().isEmpty()) {
                    okPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.green));
                    okPost.setEnabled(true);
                } else if (post_area.getText().toString().isEmpty()) {
                    okPost.setTextColor(ContextCompat.getColor(getActivity(), R.color.dot_inactive_screen2));
                    okPost.setEnabled(false);
                }
            }
        });

        okPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                String token = "Bearer " + sharedPreferences.getString("token", "noValue");
                RetrofitInterface retrofitInterface;
                retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
                Comment1 comment = new Comment1();
                comment.setBody(post_area.getText().toString());
                Call<Example> call2 = retrofitInterface.addPost(token, comment);
                call2.enqueue(new Callback<Example>() {


                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        Toast.makeText(getApplicationContext(), "You post added ", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                    }

                });

            }
        });
    /*    RecyclerView rv = (RecyclerView)getActivity().findViewById(R.id.rv);
        rv.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
       */
    }
}