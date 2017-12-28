package com.example.graduation.yallamana;

/**
 * Created by Mais

 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.graduation.yallamana.presenation.login.MainActivity;
import com.example.graduation.yallamana.presenation.signup.SignupActivity;
import com.example.graduation.yallamana.util.Navigator;
import com.example.graduation.yallamana.util.network.api.Data;
import com.example.graduation.yallamana.util.network.api.Example;

import com.example.graduation.yallamana.util.network.api.User1;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    NetworkInfo wifiCheck;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        // Do whatever here

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "the user is " + "0598569530", Toast.LENGTH_SHORT).show();
                String phone = "0598569530";
                Call<Example> call2 = retrofitInterface.getTokenLogin("0598569530");
                call2.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Example example = response.body();
                        Data data = example.getData();
                        Boolean userStatus = data.getNewUser();
                        System.out.println(userStatus.booleanValue() + "hiiiiiiiiiiiiiiiiiiiiiiii");

                        Log.d("Postsss", userStatus.booleanValue() + "");
                        Toast.makeText(getApplicationContext(), "the user is " + example.getMessage(), Toast.LENGTH_SHORT).show();

                        if (userStatus.booleanValue()) {
// if new user go and sign up
                            Intent intent = new Intent(SplashActivity.this, SignupActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // if already user go and start
                            Intent intent = new Intent(SplashActivity.this, Drawer_List.class);
                            startActivity(intent);
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                    }
                });

            }
        }, SPLASH_TIME_OUT);

    }

    public void checkWiFi(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }



}