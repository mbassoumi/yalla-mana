package com.example.graduation.yallamana;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.presenation.mytrips.TripsActivity;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Name;
import com.example.graduation.yallamana.util.network.api.NewTrip;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.api.TripAttr;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {
    private Toolbar tool;
    Context mContext;
    ProgressDialog loading;
    private Spinner spinner_from, spinner_to, date, riderNumber;
    RetrofitInterface mApiService;
private EditText userName,userNumber,userEmail;
private  Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        tool.setTitle("Edit Account ");
        tool.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");
        userEmail=(EditText)findViewById(R.id.user_email_edit);
        userName=(EditText)findViewById(R.id.user_name_edit);
        userNumber=(EditText)findViewById(R.id.user_number_edit);
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAccountActivity.this,Setting.class);
                startActivity(intent);
                finish();
            }
        });
        mApiService = ApiClient.getClient().create(RetrofitInterface.class);

        edit=(Button)findViewById(R.id.button3);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
              //  editor.putString("token", data.getToken().toString());
              //  editor.putString("type", riderInfo.getType().toString());
                editor.putString("number",userNumber.getText().toString());
                editor.putString("name", userName.getText().toString());
                editor.putString("email", userEmail.getText().toString());
                editor.commit();

            }
        });
    }
}