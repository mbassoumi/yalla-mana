package com.example.graduation.yallamana;

/**
 * Created by Mais

 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.graduation.yallamana.presenation.login.MainActivity;
import com.example.graduation.yallamana.presenation.signup.SignupActivity;
import com.example.graduation.yallamana.util.Navigator;



public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    NetworkInfo wifiCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences SP = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token= SP.getString("token","NoValue");
        String number =SP.getString("number","NoValue");

        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (wifiCheck.isConnected()) {
      
      // Do whatever here

            new Handler().postDelayed(new Runnable() {

          
      @Override
                public void run() {

          if (token.equals("NoValue") && number.equals("NoValue"))
          {
              Intent my =new Intent(SplashActivity.this,MainActivity.class);
              startActivity(my);
              finish();

          }

          else {

              Intent my =new Intent(SplashActivity.this,Drawer_List.class);
              startActivity(my);
              finish();
          }
      //Intent t= new Intent(getApplicationContext(),SignupActivity.class);
               
  //   t.putExtra("userPhon"," ");
  

                         

        }
            }, SPLASH_TIME_OUT);

        } else {
            Toast.makeText(getApplicationContext(),"check your connection !",
                    Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (token.equals("NoValue") && number.equals("NoValue"))
                    {
                        Intent my =new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(my);
                        finish();

                    }

                    else {

                        Intent my =new Intent(SplashActivity.this,Drawer_List.class);
                        startActivity(my);
                        finish();
                    }
                    Intent t= new Intent(getApplicationContext(),MainActivity.class);
                 //  t.putExtra("userPhon"," ");
                    startActivity(t);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }


}