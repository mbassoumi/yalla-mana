package com.example.graduation.yallamana.presenation.mytrips;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.OfferActivity;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.RequestActivity;
import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.util.network.api.Car;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Data;
import com.example.graduation.yallamana.util.network.api.Date;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Riders;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.api.TripAttr;
import com.example.graduation.yallamana.util.network.api.User1;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TripsActivity extends AppCompatActivity {


    ViewPager viewPager;

    TabLayout tabLayout;

    private boolean mSearchViewAdded = false;

    private Toolbar mToolbar;
    private MenuItem searchItem;
    private boolean searchActive = false;
    private FloatingActionButton fab;
    HomeViewPagerAdapter homeViewPagerAdapter;
    RetrofitInterface retrofitInterface;
    SharedPreferences sharedPreferences;
    int  selectedIndex;
    String fromC, toC, time,priceT;
    double price;
    int riderNumber;
    List<Trip> futureTrip,pastTrip,todayTrip,requestedTrip;
    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytips);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        getMytrip();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("My Trips");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User1 riderInfo=null;
                Intent intent = new Intent(TripsActivity.this,Drawer_List.class);
                intent.putExtra("userInfo",riderInfo);

                startActivity(intent);
                finish();
            }
        });

    }

    public  void getMytrip(){
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(TripsActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("loading....");
        //  progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
         sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
        Call<Example> call2 = retrofitInterface.getMyTrips(token);
        call2.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
if (response.code()==200) {
    Example example = response.body();//Data data = example.getData();
    List<Trip> future = example.getData().getFutureTrips();
    List<Trip> today = example.getData().getTodayTrips();
    List<Trip> past = example.getData().getPastTrips();
    List<Trip> request = example.getData().getRequestTrips();
    Cities from, to;
    Date dateTrip;
    int id, seatsNumber;
    User1 driver;
    Car car;
    TripAttr attributes;
    String stuats;
    Riders riders;
    boolean cancle, delete, reserve, hasUser;
    todayTrip = today;

    futureTrip = future;
    // Toast.makeText(getApplicationContext(), futureTrip.size(), Toast.LENGTH_SHORT).show();
    requestedTrip = request;
    pastTrip = past;
    homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), todayTrip, futureTrip, pastTrip, requestedTrip);

    viewPager.setAdapter(homeViewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    tabLayout.getTabAt(0).setText("Future");

    tabLayout.getTabAt(1).setText("Today");
    tabLayout.getTabAt(1).select();
    tabLayout.getTabAt(2).setText("Past");
    tabLayout.getTabAt(3).setText("Requested");
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
viewPager.getCurrentItem();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });
    progressDoalog.dismiss();

}
else{
    progressDoalog.dismiss();

    Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();
}

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                progressDoalog.dismiss();

                Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();
                call2.cancel();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            String type = sharedPreferences.getString("type", "noValue");
            if (type.equals("rider")) {
                Intent intent = new Intent(TripsActivity.this, RequestActivity.class);
                intent.putExtra("back","my");
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(TripsActivity.this, OfferActivity.class);
                intent.putExtra("back","my");
                startActivity(intent);
                finish();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

