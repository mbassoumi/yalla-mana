package com.example.graduation.yallamana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar appToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        appToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(appToolbar);
        appToolbar.setTitle(R.string.new_user);
    }
}
