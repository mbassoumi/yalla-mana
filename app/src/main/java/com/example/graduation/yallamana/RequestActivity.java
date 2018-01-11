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

public class RequestActivity extends AppCompatActivity {
    private Toolbar tool;
    Context mContext;
    ProgressDialog loading;
    private Spinner spinner_from, spinner_to, date, riderNumber;
    RetrofitInterface mApiService;
    private Button offer;
    private CheckBox wifi, music, women, smoke, men;
    private EditText time1, time2, price;
    private boolean wifi1 = false, music1 = false, women1 = false, smoke1 = false, men1 = false;
    private String number;

    int from, to;
    ArrayList<String> City_States;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_trip);
        tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        tool.setTitle("Request Trip");
        tool.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mApiService = ApiClient.getClient().create(RetrofitInterface.class);


        Intent i = getIntent();
        String typee = (String) i.getStringExtra("back");
        spinnerData();

        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typee.equals("all")) {
                    Intent intent = new Intent(RequestActivity.this, AllTripsActivity.class);
                    startActivity(intent);
                    finish();
                } else if(typee.equals("my")){
                    Intent intent = new Intent(RequestActivity.this, TripsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        wifi = (CheckBox) findViewById(R.id.wifi);
        women = (CheckBox) findViewById(R.id.women);
        men = (CheckBox) findViewById(R.id.men);
        music = (CheckBox) findViewById(R.id.music);
        smoke = (CheckBox) findViewById(R.id.smoke);
        riderNumber = (Spinner) findViewById(R.id.spinner_riders);
        //  date = (Spinner) findViewById(R.id.spinner_date);
        price = (EditText) findViewById(R.id.price);
        time1 = (EditText) findViewById(R.id.requested_trip_time1);
        time2 = (EditText) findViewById(R.id.requested_trip_time2);
        spinner_from = (Spinner) findViewById(R.id.spinner_from);
        spinner_to = (Spinner) findViewById(R.id.spinner_to);



        offer = (Button) findViewById(R.id.request_button);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifi.isChecked()) {
                    wifi1 = true;
                }
                if (music.isChecked()) {
                    music1 = true;

                }
                if (men.isChecked()) {
                    men1 = true;
                }
                if (women.isChecked()) {
                    women1 = true;
                }
                if (smoke.isChecked()) {
                    smoke1 = true;
                }
                number = riderNumber.getSelectedItem().toString();
                int num = Integer.parseInt(number);

                from = spinner_from.getSelectedItemPosition();
                to = spinner_to.getSelectedItemPosition();


                String datee = time1.getText().toString() + " " + time2.getText().toString() + ":00";


                double pricee = Double.parseDouble(price.getText().toString());
                TripAttr attr = new TripAttr(men1, smoke1, wifi1, women1, music1);
                NewTrip requestTrip = new NewTrip(num, from, to, datee, pricee, "requested", attr);
                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                String token = "Bearer " + sharedPreferences.getString("token", "noValue");
                // String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM2N2U4MGFhM2Y0ZWI2MTdmMDNhMDU5NzIxNWVhYzVjZjU1NzMwYTBjZDYwMGM5NWYwODAxZTk0MWQyNWI1ZWI0NzVlNDQ4N2JkNDIxNDE4In0.eyJhdWQiOiIxIiwianRpIjoiYzY3ZTgwYWEzZjRlYjYxN2YwM2EwNTk3MjE1ZWFjNWNmNTU3MzBhMGNkNjAwYzk1ZjA4MDFlOTQxZDI1YjVlYjQ3NWU0NDg3YmQ0MjE0MTgiLCJpYXQiOjE1MTUxOTkwNTIsIm5iZiI6MTUxNTE5OTA1MiwiZXhwIjoxNTQ2NzM1MDUyLCJzdWIiOiIyNSIsInNjb3BlcyI6W119.XYCgd3qxI8ZpGYgpFsa2e8P34y5nDQHChz5fP69rnlWQsDkVYaVJcpiJTcryn2tFsAVxRhx-XeEfMzLaqzgnOt9I-EJYwtZidtyvIOjN3PWQ4A4M67Fl1LzBnVJk8ufNhodhTeb0_r1xknG4YefPg4KBQILjs0I_RYWTEipm2MZCMcFBJaanjDNB_6PVVodY3Y7_yp-qrpdL6LT4cZtQGFW71DZNbxmDHompXYaAilyhku6vmiJutrcZsuIF9jXmaRqTqefzIsuVx-hC8dvBdmoJ_vJTEjGVseYoKiAXLGXmZlGdESUu9b93NPnuZJZTjtqSfjIvp5A13YxtZnDbd82ZmPn0q4nfNH2RsCpDpE8FoyzcsMT9VPTCLVAYmPrMvH5xhUHMRHTCYVl1042ym3ZVOmdPIx6SQ86aCT5EABVvg2Ra55naAZmtM8_19fBnplAcY6LsK-Ijj7J9Mc7R2Yo6uZrn7ast2kqkn5a7YW5Q7XRQu5-PUJJ5WMKrM9FnXgNy7US4fCSpTw3novppkJolUFCMjIVxMRUQh5A8y6UgOvMU6OhcofT4TvN0Pe7VFB8EAg2gdwlydRYK3kW_fWJYXAOoA-9l2s2FnPsN11AiYViZFtqfwNCkijhQlEBolcWT-0vz7gwcnWA52l9IzYSQr9ATI07ozKiTmJ-grk8";
                Call<Trip> call2 = mApiService.setRequestTrip(token, requestTrip);
                call2.enqueue(new Callback<Trip>() {
                    @Override
                    public void onResponse(Call<Trip> call, Response<Trip> response) {
                        Trip newTrip = response.body();
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Requested :)", Toast.LENGTH_SHORT).show();
                            if (typee.equals("all")) {
                                Intent intent = new Intent(RequestActivity.this, AllTripsActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(RequestActivity.this, TripsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), " error ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Trip> call, Throwable t) {

                    }
                });
            }
        });

// set the spinner data programmatically, from a string array or list

        // (1) get a reference to the spinner


//        spinner_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedName = parent.getItemAtPosition(position).toString();
////                requestDetailDosen(selectedName);
////                Toast.makeText(mContext, selectedName, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spinner_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedName = parent.getItemAtPosition(position).toString();
////                requestDetailDosen(selectedName);
//                Toast.makeText(mContext, selectedName, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        //loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
    }

    private void spinnerData() {

        Call<Example> call2 = mApiService.getCities();
        // Toast.makeText(getApplicationContext(), "imagee"+encodedImage, Toast.LENGTH_SHORT).show();

        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Example example = response.body();

                List<Cities> city = example.getData().getCities();
                City_States = new ArrayList<>();
                Name name;
                for (int i = 0; i < city.size(); i++) {
                    name = city.get(i).getName();
                    City_States.add(name.getEn());

                }

                ArrayAdapter<String> sp=   new ArrayAdapter<String>(
                        RequestActivity.this, android.R.layout.simple_list_item_1, City_States);
                sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_from.setAdapter(sp);
                spinner_to.setAdapter(sp);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}