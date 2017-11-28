package com.example.graduation.yallamana.presenation.signup;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.graduation.yallamana.presenation.login.LoginActivity;
import com.example.graduation.yallamana.R;
import butterknife.BindView;

/**
 * Created by Mais

 */


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    ImageButton driverButton;
    ImageButton riderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle(R.string.new_user);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Back clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        riderButton = (ImageButton) findViewById(R.id.rider);

        riderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


            }

        });

        driverButton = (ImageButton) findViewById(R.id.driver);

        driverButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(SignupActivity.this, DriverActivity.class);
                startActivity(intent);
                finish();


            }

        });
    }

}

