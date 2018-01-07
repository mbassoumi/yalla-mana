package com.example.graduation.yallamana.presenation.signup;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.NewUser;
import com.example.graduation.yallamana.util.network.api.User1;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.login.widget.ProfilePictureView.TAG;

public class RiderFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RetrofitInterface retrofitInterface;
    NewUser riderUser;
    TextView mResponse;

    public RiderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rider, container, false);
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        Intent i = getActivity().getIntent();
        riderUser = (NewUser) i.getSerializableExtra("riderUser");
        Button upButton = (Button) view.findViewById(R.id.skip);
        upButton.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip:
                skip();
                break;
        }
    }


    private void skip() {

//        Call<User1> call2 = retrofitInterface.setUserInfo(riderUser);
//        call2.enqueue(new Callback<User1>() {
//            @Override
//            public void onResponse(Call<User1> call, Response<User1> response) {
//
//
//                Log.i(TAG, "post submitted to API." + response.body().toString());
//                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
//                moveToMyProfile();
//                    }
//
//
//            @Override
//            public void onFailure(Call<User1> call, Throwable t) {
//
//                call2.cancel();
//            }
//        });


    }

    private void moveToMyProfile() {
        Intent i = new Intent(getActivity(),Drawer_List.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);

    }
}