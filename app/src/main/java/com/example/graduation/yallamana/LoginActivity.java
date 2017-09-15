package com.example.graduation.yallamana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.signupButton)
    Button SignupButton;
    @BindView(R.id.user_phone)
    EditText UserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.yallaButton)
    void yallaGo() {

    }

    @OnClick(R.id.signupButton)
    void signupGo() {
        Navigator.navigateTosignup(LoginActivity.this);
        finish();
    }


}