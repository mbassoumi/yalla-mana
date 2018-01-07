package com.example.graduation.yallamana.presenation.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.Data;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.NewUser;
import com.example.graduation.yallamana.util.network.api.User1;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.login.widget.ProfilePictureView.TAG;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    private SignInButton sign;
    private GoogleApiClient client;
    private static final int REQ_CODE = 9001;
    private EditText firstName, lastName, email, phone;
    private Toolbar mToolBar;
    private ImageButton driverButton, riderButton;
    private ImageView userImage;
    private Spinner genderSpinner;
    private Bitmap image, bitmap;
    private Uri filePath;
    private String selectedFilePath ,gender, imagePath, imgString, authant_phone;
    NetworkInfo wifiCheck;


    RetrofitInterface retrofitInterface;
    // [START declare_auth]

    private FirebaseAuth mAuth;

    // [END declare_auth]


    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        authant_phone = bundle.getString("userPhone");

     //   Toast.makeText(SignupActivity.this, authant_phone, Toast.LENGTH_SHORT).show();

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle(R.string.new_user);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        //  mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(mToolBar);
        mAuth = FirebaseAuth.getInstance();
// Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        genderSpinner = (Spinner) findViewById(R.id.spinner5);
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        firstName = (EditText) findViewById(R.id.firstName1);
        lastName = (EditText) findViewById(R.id.lastName1);
        email = (EditText) findViewById(R.id.email1);
        phone = (EditText) findViewById(R.id.mobile);
        phone.setText(authant_phone);
       // phone.setEnabled(false);


      //  String hint1 = firstName.getHint().toString();
      //  String hint2 = lastName.getHint().toString();
       // firstName.setHint(Html.fromHtml(hint1 + "<font color=\"red\">*</font>"));
      //  lastName.setHint(Html.fromHtml(hint2 + "<font color=\"red\">*</font>"));
        userImage = (ImageView) findViewById(R.id.userImage);

//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SignupActivity.this, "Back clicked!", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });


        userImage.setOnClickListener(new ImageView.OnClickListener()

        {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
/////////////////////////////////////rider//////////////////////////////////////////////////////////
        riderButton = (ImageButton) findViewById(R.id.rider);

        riderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (firstName.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "First name cant be empty!", Toast.LENGTH_LONG).show();

                } else if (lastName.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Last name cant be empty!", Toast.LENGTH_LONG).show();


                } else {
//                Intent intent = new Intent(SignupActivity.this,Drawer_List.class);
//                startActivity(intent);
//                finish();

                    NewUser riderUser;
                    String userName = firstName.getText().toString() + " " + lastName.getText().toString();
                    String number = phone.getText().toString();
                    String emaill = email.getText().toString();

                    //Adding setOnItemSelectedListener method on spinner.
                    String userGender = genderSpinner.getSelectedItem().toString();
                    Log.i(TAG, "gender" + userGender);


                    riderUser = new NewUser(userName, number, "rider", userGender, emaill);

                    skip(riderUser);
//                    getIntent().putExtra("riderUser", riderUser);
//
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    RiderFragment f1 = new RiderFragment();
//                    ft.replace(R.id.frame_container, f1);
//                    ft.commit();
                }
            }


        });
////////////////////driverrr//////////////////////////////////////////////////////////////////
        driverButton = (ImageButton) findViewById(R.id.driver);

        driverButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (firstName.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "First name cant be empty!", Toast.LENGTH_LONG).show();

                } else if (lastName.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Last name cant be empty!", Toast.LENGTH_LONG).show();

                }  else {
                    String userName = firstName.getText().toString() + " " + lastName.getText().toString();
                    String number = phone.getText().toString();
                    String emaill = email.getText().toString();

                    String userGender = genderSpinner.getSelectedItem().toString();

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


                    NewUser driverUser = new NewUser(userName, number, emaill, userGender, "photo url", "driver", null);


                    Intent intent = new Intent(SignupActivity.this, DriverActivity.class);
                    intent.putExtra("driverUser", driverUser);
                    startActivity(intent);
                    finish();


                }
            }

        });

        sign = (SignInButton) findViewById(R.id.sign_in_button);

        sign.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

        }

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
        startActivityForResult(signInIntent, REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            userImage.setImageURI(targetUri);
            imagePath = targetUri.getPath().toString();
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                userImage.setImageBitmap(bitmap);
                Picasso.with(getApplicationContext())
                        .load(filePath)
                        .resize(600, 150)
                        .centerInside()
                        .into(userImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String name = acct.getDisplayName().toString();
            String[] parts = name.split(" ");
            String part1 = parts[0];
            String part2 = parts[1]; // 034556
            firstName.setText(parts[0].toString());
            lastName.setText(parts[1].toString());
            email.setText(acct.getEmail());

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);


        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    ////   reguest for new user as rider
    private void skip(NewUser riderUser) {

        Call<Example> call2 = retrofitInterface.setUserInfo(riderUser);
        // Toast.makeText(getApplicationContext(), "imagee"+encodedImage, Toast.LENGTH_SHORT).show();

        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                Data data = example.getData();
                Boolean userStatus = data.getNewUser();
                User1 riderInfo = data.getUser();
                if (response.code() == 200) {
                    SharedPreferences sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", data.getToken().toString());
                    editor.putString("type", riderInfo.getType().toString());
                    editor.putString("number", riderInfo.getPhone().toString());
                    editor.putString("name", riderInfo.getName().toString());

                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Welcome to your profile :)", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignupActivity.this, Drawer_List.class);


                   // i.putExtra("userInfo",riderInfo);
                    startActivity(i);

                    finish();
                } else if (response.code() == 400) {
                    Toast.makeText(getApplicationContext(), "You are already registed !", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Ops! something goes wrong", Toast.LENGTH_LONG).show();

                }


            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                call2.cancel();
            }
        });


    }


}
