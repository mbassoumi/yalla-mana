package com.example.graduation.yallamana.presenation.post;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduation.yallamana.Drawer_List;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.presenation.post.fragments.AddPostFragment;
import com.example.graduation.yallamana.presenation.post.utils.HomeViewPagerAdapter;
import com.example.graduation.yallamana.presenation.post.utils.PostAdapter;
import com.example.graduation.yallamana.util.network.api.Data;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Post;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity  {


   private Menu menu;
    private List<Post> allPosts;
    private PostAdapter postAdapter;
    private Random random;
    ProgressDialog progressDoalog;
    List <Post> myPost;
    TextView user_name;
    CollapsingToolbarLayout collapsingToolbarLayout;
    SharedPreferences sharedPreferences;
   TabLayout tabLayout;
    ViewPager viewPager;
    String name;
    HomeViewPagerAdapter homeViewPagerAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
       viewPager=(ViewPager)findViewById(R.id.viewPager);
       user_name =(TextView)findViewById(R.id.user_name);
        tabLayout=(TabLayout)findViewById(R.id.tabs);

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        name =  sharedPreferences.getString("name", "noValue");


        progressDoalog = new ProgressDialog(PostActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("loading....");
        //  progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
     fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.hide();
                AddPostFragment fragment = new AddPostFragment();
                FragmentManager fragmentManager =getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


      getPosts();
        collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

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



                    showOption(R.id.action_add);
                } else if (isShow) {
                    isShow = false;

                    hideOption(R.id.action_add);
                }
            }
        });
    }


    private void getPosts() {

        // show it
        progressDoalog.show();
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");
        RetrofitInterface retrofitInterface;
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        Call<Example> call2 = retrofitInterface.getMyPosts(token);
        call2.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==200) {
                    Example example = response.body();
                    Data data = example.getData();
                    myPost = data.getPosts();
                    //find view by id and attaching adapter for the RecyclerView
                    getAll();
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


            }
        });


    }

    private void getAll() {
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "noValue");
        RetrofitInterface retrofitInterface;
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        Call<Example> call2 = retrofitInterface.getAllPosts(token);
        call2.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code()==200) {
                    Example example = response.body();
                    Data data = example.getData();
                    allPosts = data.getPosts();

                    progressDoalog.dismiss();}
                else{
                    progressDoalog.dismiss();
                    Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getApplicationContext(), "something goes wrong", Toast.LENGTH_LONG).show();


            }
        });
        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), myPost, allPosts);
        viewPager.setAdapter(homeViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(PostActivity.this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(1).select();


        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications_black_24dp);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.getCurrentItem();
//                        AppBarLayout appbar =(AppBarLayout)findViewById(R.id.appbar);
//                        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
                tab.getIcon().setColorFilter(ContextCompat.getColor(PostActivity.this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
//                        if (tabLayout.getTabAt(1).isSelected()||tabLayout.getTabAt(2).isSelected()){
//                            CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)appbar.getLayoutParams();
//                            params.height = 0; // COLLAPSED_HEIGHT
//                            appbar.setLayoutParams(params);
//                           //toolbar.setTitle(name);
//                            appbar.setExpanded(false);
//                        }
//                        else{
//                            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
//                            params.height =290*3; // EXPANDED_HEIGHT
//                          user_name.setText(name);
//
//                            appbar.setLayoutParams(params);
//                            appbar.setExpanded(true);}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
            fab.hide();
            AddPostFragment fragment = new AddPostFragment();
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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