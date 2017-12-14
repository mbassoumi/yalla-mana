package com.example.graduation.yallamana.presenation.alltrips;

import android.content.Intent;
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

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.Request_trip;
import com.example.graduation.yallamana.presenation.post.PostActivity;
import com.example.graduation.yallamana.presenation.signup.DriverActivity;
import com.example.graduation.yallamana.util.network.api.Trip;

import java.util.ArrayList;
import java.util.List;

public class AllTripsActivity extends AppCompatActivity {
    private Menu menu;
    private RecyclerView recyclerView;
    private TripsAdapter adapter;
    private List<Trip> tripList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alltrips);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Trips");
        ///////////////////////////

       toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTripsActivity.this,Drawer_List.class);
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
                Intent intent = new Intent(AllTripsActivity.this,Request_trip.class);
                startActivity(intent);
                finish();
        }
        });



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_alltrips);

        tripList = new ArrayList<>();
        adapter = new TripsAdapter(this, tripList);

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
        prepareTrips();

//        try {
//            Glide.with(this).load(R.drawable.map_trips).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
    private void prepareTrips() {
        int[] covers = new int[]{
                R.drawable.person1,

                R.drawable.person2,
                R.drawable.person2,
                R.drawable.person2,
                R.drawable.person2,


        };

        Trip a = new Trip("Ramallah", "13-12-2017", covers[0],"Nablus");
        tripList.add(a);

        a = new Trip("Ramallah", "13-12-2017", covers[1],"Nablus");
        tripList.add(a);
        a = new Trip("Ramallah", "3-12-2017", covers[2],"Nablus");
        tripList.add(a);
        a = new Trip("Ramallah", "13-12-2017", covers[0],"Nablus");
        tripList.add(a);
        a = new Trip("Al-Bireh","13-12-2017", covers[3],"Nablus");
        tripList.add(a);
        a = new Trip("Ramallah", "13-12-2017", covers[1],"Nablus");
        tripList.add(a);
        a = new Trip("Ramallah", "13-12-2017", covers[2],"Nablus");
        tripList.add(a);
        a = new Trip("Ramallah","13-12-2017", covers[0],"Nablus");
        tripList.add(a);
        a = new Trip("Hebron","13-12-2017", covers[0],"Jericho");
        tripList.add(a);
        a = new Trip("Jericho", "13-12-2017", covers[1],"Nablus");
        tripList.add(a);

        adapter.notifyDataSetChanged();
    }

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

            Intent intent = new Intent(this,Request_trip.class);
            startActivity(intent);
            finish();
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
