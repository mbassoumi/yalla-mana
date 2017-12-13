package com.example.graduation.yallamana.presenation.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.Request_trip;
import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.presenation.signup.SignupActivity;
import com.example.graduation.yallamana.util.Navigator;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.login.fragment.verificationFragment;
import com.example.graduation.yallamana.util.UiUtils;
import com.facebook.login.widget.LoginButton;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mais
 */

public class LoginActivity extends AppCompatActivity implements LoginView {


    Button signupButton;
    EditText userPhone;
    Toolbar toolbar;
//    @BindView(R.id.login_facebook)
//    LoginButton loginButton;
//    CallbackManager callbackManager;
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        callbackManager = CallbackManager.Factory.create();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        LoginFB();

        signupButton=(Button)findViewById(R.id.signupButton);
        Button yalla_button =(Button)findViewById(R.id.yallaButton);

       signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        yalla_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,Drawer_List.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @OnClick(R.id.yallaButton)
    // loogin with user number
    public void loginButtonClicked() {
//
        Navigator.navigateToPost(this);
        finish();

    }

    @OnClick(R.id.signupButton)
    void set() {

        Toast.makeText(LoginActivity.this, "signup",
                Toast.LENGTH_SHORT).show();
        Navigator.navigateToPost(this);
        finish();


    }

    // those method to check if user have account or not....
    public void loginSuccess() {
        progressDialog.hide();


        verificationFragment fragment = new verificationFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contaner, fragment);
        transaction.commit();
    }

    @Override
    public void loginFailed() {
        progressDialog.hide();
        Toast.makeText(this, "Login Invalid: Username or password is wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateFail() {
        progressDialog.hide();
        Toast.makeText(this, "Login Invalid: User must be 4 letters or longer, and password 6 or more", Toast.LENGTH_SHORT).show();
    }

   /* // login withf acebook account
    private void LoginFB() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }*/
}