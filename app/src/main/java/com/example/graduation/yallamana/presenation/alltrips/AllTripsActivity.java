package com.example.graduation.yallamana.presenation.alltrips;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.OfferActivity;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.RequestActivity;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTripsActivity extends AppCompatActivity {
    private Menu menu;
    private RecyclerView recyclerView;
    private TripsAdapter adapter;


    private List<Trip> tripeList;
    private SharedPreferences sharedPreferences;
    RetrofitInterface retrofitInterface;
    String fromC, toC, time, priceT;
    double price;
    int riderNumber;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alltrips);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Trips");
        ///////////////////////////
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTripsActivity.this, Drawer_List.class);
                User1 user = null;
                intent.putExtra("userInfo", user);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Request trip", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String type = sharedPreferences.getString("type", "noValue");
                if (type.equals("rider")) {
                    Intent intent = new Intent(AllTripsActivity.this, RequestActivity.class);
                    intent.putExtra("back", "all");

                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(AllTripsActivity.this, OfferActivity.class);
                    intent.putExtra("back", "all");

                    startActivity(intent);
                    finish();
                }
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_alltrips);

        tripeList = new ArrayList<>();
        adapter = new TripsAdapter(this, tripeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_add);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_add);
                }
            }
        });
        // prepareTrips();
        getAllTrips();
//        try {
//            Glide.with(this).load(R.drawable.map_trips).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void getAllTrips() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(AllTripsActivity.this);
        progressDoalog.setMax(100);
         progressDoalog.setMessage("loading....");
        //  progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
       sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");

        Call<Example> call2 = retrofitInterface.getTrips(token);
        call2.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
            if (response.code()==200){
                Example example = response.body();
                Data data = example.getData();
                List<Trip> trips = data.getTrips();
                Cities from, to;

                Date dateTrip;

                int id, seatsNumber;
                User1 driver;
                Car car;
                TripAttr attributes;
                String stuats;
                Riders riders;
                boolean cancle, delete, reserve, hasUser;
                for (int i = 0; i < trips.size(); i++) {
                    from = trips.get(i).getStartPoint();
                    to = trips.get(i).getEndPoint();
                    fromC = from.getName().getEn().toString();
                    toC = to.getName().getEn().toString();
                    dateTrip = trips.get(i).getDate();
                    time = dateTrip.getDate() + " " + dateTrip.getTime();
                    price = trips.get(i).getPrice();
                    priceT = price + " Nis";
                    seatsNumber = trips.get(i).getSeatsNumber();
                    id = trips.get(i).getId();
                    driver = trips.get(i).getDriver();
                    car = trips.get(i).getCar();
                    attributes = trips.get(i).getAttributes();
                    stuats = trips.get(i).getStuats();
                    riders = trips.get(i).getRiders();
                    cancle = trips.get(i).isCancle();
                    delete = trips.get(i).isDelete();
                    reserve = trips.get(i).isReserve();
                    hasUser = trips.get(i).isHasUser();

                    Trip a = new Trip(id, seatsNumber, from, to, driver, car, dateTrip, price, stuats, attributes, riders, cancle, delete, reserve, hasUser);
                    tripeList.add(a);
                }

                adapter.notifyDataSetChanged();
                progressDoalog.dismiss();}
else{
    progressDoalog.dismiss();
    Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();

}

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                progressDoalog.dismiss();
                call2.cancel();
            }
        });
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar from on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the from when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    /**

     */

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.post_toolbar, menu);
        hideOption(R.id.action_add);
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
            // check if user or driver then decid to offer or request
            String type = sharedPreferences.getString("type", "noValue");
            if (type.equals("rider")) {
                Intent intent = new Intent(AllTripsActivity.this, RequestActivity.class);
                intent.putExtra("back", "all");

                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(AllTripsActivity.this, OfferActivity.class);
                intent.putExtra("back", "all");

                startActivity(intent);
                finish();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}
