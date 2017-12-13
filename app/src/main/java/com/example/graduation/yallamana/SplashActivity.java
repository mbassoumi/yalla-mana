package com.example.graduation.yallamana;

/**
 * Created by Mais

 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.graduation.yallamana.presenation.login.MainActivity;
import com.example.graduation.yallamana.util.Navigator;



public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent t= new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(t);

            }
        }, SPLASH_TIME_OUT);
    }

}