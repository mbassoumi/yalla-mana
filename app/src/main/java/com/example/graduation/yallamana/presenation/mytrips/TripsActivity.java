package com.example.graduation.yallamana.presenation.mytrips;

import android.content.Intent;
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


import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;


public class TripsActivity extends AppCompatActivity {


    ViewPager viewPager;

    TabLayout tabLayout;

    private boolean mSearchViewAdded = false;

    private Toolbar mToolbar;
    private MenuItem searchItem;
    private boolean searchActive = false;
    private FloatingActionButton fab;
    HomeViewPagerAdapter homeViewPagerAdapter;
    int  selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytips);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("My Trips");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripsActivity.this,Drawer_List.class);
                startActivity(intent);
                finish();
            }
        });

        tabLayout.getTabAt(0).setText("TODAY");
        tabLayout.getTabAt(0).select();

        tabLayout.getTabAt(1).setText("FUTURE");
        tabLayout.getTabAt(2).setText("PAST");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        searchItem = menu.findItem(R.id.search);
//        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                mSearchView.display();
//                openKeyboard();
//                return true;
//            }
//        });
//        if(searchActive)
//            mSearchView.display();
//        return true;
//
//    }
//
//    private void openKeyboard(){
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
//                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
//            }
//        }, 200);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onSearch(String query) {
//
//    }
//
//    @Override
//    public void searchViewOpened() {
//        Toast.makeText(TripsActivity.this,"Search View Opened",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void searchViewClosed() {
//        Util.showSnackBarMessage(fab,"Search View Closed");
//    }
//
//    @Override
//    public void onItemClicked(String item) {
//        Toast.makeText(TripsActivity.this,item + " clicked", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onScroll() {
//
//    }
//
//    @Override
//    public void error(String localizedMessage) {
//
//    }
//
//    @Override
//    public void onCancelSearch() {
//        Util.showSnackBarMessage(fab,"Search View Cleared");
//        searchActive = false;
//        mSearchView.hide();
//    }

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
            //check rider or driver
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

