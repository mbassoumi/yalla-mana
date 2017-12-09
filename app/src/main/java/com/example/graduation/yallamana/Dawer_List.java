package com.example.graduation.yallamana;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Dawer_List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dawer__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dawer__list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
 @SuppressWarnings("StatementWithEmptyBody")
 @Override
 public boolean onNavigationItemSelected(MenuItem item) {
     // Handle navigation view item clicks here.
     int id = item.getItemId();

     /*This drawer list can use fragment to show its options*/

     if (id == R.id.M_mytrip) {
        /* setTitle("My trip");
        Mytrip_f fa = new Mytrip_f();

         android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.fram , fa,"Mytrip_f");
         ft.commit();*/
         Intent t = new Intent(getApplicationContext(),Trip_information.class);
         finish();
         startActivity(t);

     } else if (id == R.id.M_Trip) {
        /* setTitle("Trip");
         Trip_f fa = new Trip_f();
         android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.fram , fa,"Trip_f");
         ft.commit();*/

     } else if (id == R.id.M_setting) {


     } else if (id == R.id.M_help) {

     } else if (id == R.id.M_mywallet) {
         setTitle("My wallet");
         Mywallet_f fa = new Mywallet_f();
         android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.fram , fa,"Mywallet");
         ft.commit();


     } else if (id == R.id.M_post) {

     }
     else if (id == R.id.M_be_driver) {

     }

     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     drawer.closeDrawer(GravityCompat.START);
     return true;
 }
}
