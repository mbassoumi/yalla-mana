package com.example.graduation.yallamana.presenation.post;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.post.utils.HomeViewPagerAdapter;

public class PostActivity extends AppCompatActivity  {


    private Menu menu;
    TextView user_name;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
       ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);
       user_name =(TextView)findViewById(R.id.user_name);
      final TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);


        HomeViewPagerAdapter homeViewPagerAdapter;

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this,Drawer_List.class);
                startActivity(intent);
                finish();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications_black_24dp);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                AppBarLayout appbar =(AppBarLayout)findViewById(R.id.appbar);
                Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
                tab.getIcon().setColorFilter(ContextCompat.getColor(PostActivity.this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                if (tabLayout.getTabAt(1).isSelected()||tabLayout.getTabAt(2).isSelected()){
                    CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)appbar.getLayoutParams();
                    params.height = 10; // COLLAPSED_HEIGHT

                    appbar.setLayoutParams(params);
                    appbar.setExpanded(false);
                }
                else{
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
                    params.height =370; // EXPANDED_HEIGHT

                    appbar.setLayoutParams(params);
                    appbar.setExpanded(true);}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
                    collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle(user_name.getText().toString());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    showOption(R.id.action_add);



                    showOption(R.id.action_add);
                } else if (isShow) {
                    isShow = false;
                   collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle("");

                    hideOption(R.id.action_add);
                }
            }
        });
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