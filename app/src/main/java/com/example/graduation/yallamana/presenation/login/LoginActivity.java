package com.example.graduation.yallamana.presenation.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
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
import com.example.graduation.yallamana.util.Navigator;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.login.fragment.verificationFragment;
import com.example.graduation.yallamana.util.UiUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mais
 */

public class LoginActivity extends AppCompatActivity implements LoginView {


    @BindView(R.id.signupButton)
    Button signupButton;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.toolbar)
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
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
//        callbackManager = CallbackManager.Factory.create();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        LoginFB();


    }


    @OnClick(R.id.yallaButton)
    // loogin with user number
    public void loginButtonClicked() {
        progressDialog = ProgressDialog.show(this, "Logging in ...", "Please Wait");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                 String phone = userPhone.getText().toString();
                 loginPresenter.submitLogin(phone);
                loginSuccess();
            }
        }, 2000);

    }

    @OnClick(R.id.signupButton)
    void set() {

        Toast.makeText(LoginActivity.this, "signup",
                Toast.LENGTH_SHORT).show();
        Navigator.navigateTosignup(this);
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