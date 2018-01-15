package com.example.graduation.yallamana;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.presenation.mytrips.TripsActivity;
import com.example.graduation.yallamana.util.network.api.Car;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Data;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Trip_information extends AppCompatActivity {

    private Toolbar tool;
    private int tripId;
    private String status;
    private RetrofitInterface retrofitInterface;
    SharedPreferences sharedPreferences;

    private Trip trip;
    private TextView from, to, price, seatsNumber, date, time,dataStill,timeStill, driverName, driverNumber,curentRiders;
    private Button reserve;
    private ImageButton carInfo, music, wifi, smoke, men, women;
    Cities fromC,toC;
    Car car;
    int riderNumbers =0;
    private String token;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_information);
        Bundle bundle = getIntent().getExtras();
        tripId = bundle.getInt("tripId");
       riderNumbers = bundle.getInt("ridersNumber");
        status= bundle.getString("tripsStatus");
        tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getSupportActionBar().setTitle(R.string.trip_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
        from = (TextView) findViewById(R.id.from_info);
        to = (TextView) findViewById(R.id.to_info);
        price = (TextView) findViewById(R.id.price);
        seatsNumber = (TextView) findViewById(R.id.number_rider);
        date = (TextView) findViewById(R.id.trip_time1);
        time = (TextView) findViewById(R.id.trip_time2);
        driverName = (TextView) findViewById(R.id.driver_name);
        driverNumber = (TextView) findViewById(R.id.driver_number);
        carInfo = (ImageButton) findViewById(R.id.imageButton_info);
        wifi = (ImageButton) findViewById(R.id.image_wifi);
        men = (ImageButton) findViewById(R.id.image_men);
        women = (ImageButton) findViewById(R.id.image_women);
        smoke = (ImageButton) findViewById(R.id.image_smoke_info);
        music = (ImageButton) findViewById(R.id.image_music_info);
      //  timeStill=(TextView)findViewById(R.id.trip_still_time1) ;
        curentRiders=(TextView)findViewById(R.id.current_number_rider);
        reserve=(Button)findViewById(R.id.reserve);
        if(status.equals("all")){
            reserve.setText("Reserve");

        }
        else if(status.equals("request")){
            String type = sharedPreferences.getString("type", "noValue");
            if (type.equals("rider")) {
                reserve.setVisibility(View.INVISIBLE);
            }
            reserve.setText("Accept");
        }
       else if (status.equals("past")){
            reserve.setText("Rating");

        }
        else if (status.equals("future") || status.equals("today")){
            reserve.setText("Cancle");

        }

//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//      //  System.out.println(dtf.format(now));


        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equals("all")){
                    startActivity(new Intent(getApplicationContext(), AllTripsActivity.class));

                }
                else if (status.equals("past")){
                    startActivity(new Intent(getApplicationContext(), TripsActivity.class));

                }
                else if (status.equals("future") || status.equals("today")|| status.equals("past")||status.equals("request")){
                    startActivity(new Intent(getApplicationContext(), TripsActivity.class));

                }
            }
        });
        getTripinfo();
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("type", car.getModelType().toString());
//        editor.putString("year", car.getModelYear().toString());
//        editor.commit();
        token = "Bearer " + sharedPreferences.getString("token", "noValue");


      reserve.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (reserve.getText().equals("Reserve")) {
//                   if (curentRiders.getText().equals(seatsNumber.getText().toString())) {
//                      Toast.makeText(getApplicationContext(), "Sorry the trip now full !", Toast.LENGTH_SHORT).show();
//
//                  }

                  reserveTrip();
              } else if (reserve.getText().equals("Cancle"))

              {
                  cancleTrip();
              } else if (reserve.getText().equals("Accept")) {

                  acceptTrip();
              }


          }


        });

        //this for the popup windows using the imagebutton to show the driver info
        carInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction manage = getSupportFragmentManager().beginTransaction();
                Pop_car_info pop = new Pop_car_info();
//                Bundle b2 = new Bundle();
//                b2.putParcelableArrayList("car", car);
                pop.show(manage, null);
            }
        });

    }

    private void acceptTrip() {
        Call<Example> call2 = retrofitInterface.acceptTrip(token, tripId);
        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                if (response.code() == 200) {
                    reserve.setEnabled(false);
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "accepted", Toast.LENGTH_LONG).show();
                call2.cancel();
            }

        });
    }

    private void cancleTrip() {
        Call<Example> call2 = retrofitInterface.cancleTrip(token, tripId);
        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                if (response.code() == 200) {
                    reserve.getText().equals("Reserve");
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    //
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "canceled", Toast.LENGTH_LONG).show();
                call2.cancel();

            }

        });
    }

    private void reserveTrip() {
        Call<Example> call2 = retrofitInterface.reverseTrip(token, tripId);
        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                if (response.code() == 200) {
                    reserve.getText().equals("Cancle");
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "reserved ", Toast.LENGTH_LONG).show();

                call2.cancel();

            }

        });
    }

    private void getTripinfo() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Trip_information.this);
        progressDoalog.setMax(100);
         progressDoalog.setMessage(" loading....");
        //  progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");

        Call<Example> call2 = retrofitInterface.getTripInfo(token, tripId);
        call2.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code()==200) {
                    Example example = response.body();
                    Data data = example.getData();
                    trip = data.getTrip();

                    fromC = trip.getStartPoint();
                    toC = trip.getEndPoint();

                    from.setText(fromC.getName().getEn().toString());
                    to.setText(toC.getName().getEn().toString());
                    price.setText(trip.getPrice() + " Nis");
                    seatsNumber.setText(trip.getSeatsNumber() + "");
                    if(trip.getDriver()==null){
                        driverNumber.setText("-------");
                        driverName.setText("no driver ");}
                        else{
                        driverNumber.setText(trip.getDriver().getPhone());
                    driverName.setText(trip.getDriver().getName());}

                    date.setText(trip.getDate().getDate());
                    time.setText(trip.getDate().getTime());
                  //  timeStill.setText("2018-1-8 11:00");
                    car = trip.getCar();
                    curentRiders.setText(riderNumbers + "");
//                    if (!trip.isCancle()){
//                        reserve.setVisibility(View.INVISIBLE);
//                    }

                    if (trip.isHasUser()) {
                        reserve.setText("Cancle");
                    }

                    boolean boy =trip.getAttributes().isBoys();
                    if (trip.getAttributes().isBoys()) {
                        men.setImageDrawable(getResources().getDrawable(R.drawable.ic_men_green_24dp));


                    }
                    if (trip.getAttributes().isGirls()) {
                        women.setImageDrawable(getResources().getDrawable(R.drawable.ic_face_green_24dp));


                    }
                    if (!trip.getAttributes().isSmoke()) {
                        smoke.setImageDrawable(getResources().getDrawable(R.drawable.ic_smoke_free_green_24dp));


                    }
                    if (trip.getAttributes().isWifi()) {
                        wifi.setImageDrawable(getResources().getDrawable(R.drawable.ic_wifi_green_8dp));


                    }
                    if (trip.getAttributes().isMusic()) {
                        music.setImageDrawable(getResources().getDrawable(R.drawable.ic_music_note_green_24dp));


                    }
                    if (trip.getAttributes().isBoys()) {
                        men.setImageDrawable(getResources().getDrawable(R.drawable.ic_men_green_24dp));


                    }
                    if (trip.getAttributes().isGirls()) {
                        women.setImageDrawable(getResources().getDrawable(R.drawable.ic_face_green_24dp));


                    }
                    if (!trip.getAttributes().isSmoke()) {
                        smoke.setImageDrawable(getResources().getDrawable(R.drawable.ic_smoke_free_green_24dp));


                    }
                    if (trip.getAttributes().isWifi()) {
                        wifi.setImageDrawable(getResources().getDrawable(R.drawable.ic_wifi_green_8dp));


                    }
                    if (trip.getAttributes().isMusic()) {
                        music.setImageDrawable(getResources().getDrawable(R.drawable.ic_music_note_green_24dp));


                    }
                    progressDoalog.dismiss();
                }
           else {
                    progressDoalog.dismiss();

                    Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Example> call, Throwable t)
            {
                progressDoalog.dismiss();
                Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();

                call2.cancel();
            }
        });

    }


}
