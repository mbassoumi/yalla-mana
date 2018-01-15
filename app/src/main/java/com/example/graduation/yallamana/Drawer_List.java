package com.example.graduation.yallamana;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;
import com.example.graduation.yallamana.presenation.mytrips.TripsActivity;
import com.example.graduation.yallamana.presenation.post.PostActivity;
import com.example.graduation.yallamana.presenation.signup.DriverActivity;
import com.example.graduation.yallamana.presenation.signup.ToBeDriverActivity;
import com.example.graduation.yallamana.util.network.api.NewUser;
import com.example.graduation.yallamana.util.network.api.User1;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;

public class Drawer_List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView ;
    TextView name,number;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dawer__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   toolbar.setBackgroundColor(getResources().getColor(R.color.white));
//
      Intent i= getIntent();
      User1 riderUser = (User1) i.getSerializableExtra("userInfo");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
       navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        View header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.user_namee);
        number = (TextView) header.findViewById(R.id.user_numberr);
        LinearLayout linear =(LinearLayout)header.findViewById(R.id.header);
        //if (!riderUser.getName().toString().equals(null)) {
//    name.setText(riderUser.getName().toString());
////
//    number.setText(riderUser.getName().toString());
//}
//     else{
    name.setText(sharedPreferences.getString("name","noValue"));
    number.setText(sharedPreferences.getString("number","noValue"));

//
        if (riderUser!=null) {
            CircularImageView profile_image = (CircularImageView) header.findViewById(R.id.profile_image);

//            Glide.with(this)
//                .load(riderUser.getPhoto())
//                .into(profile_image);}
        }
//        TextView name = (TextView) header.findViewById(R.id.user_namee);
//        TextView number = (TextView) header.findViewById(R.id.user_numberr);
//
//        name.setText(riderUser.getName().toString());
//        number.setText(riderUser.getPhone().toString());
//       linear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Drawer_List.this,EditAccountActivity.class);
//               startActivity(intent);
//               intent.putExtra("hi","edit");
//               finish();
//
//
//            }
//        });
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
 String type = sharedPreferences.getString("type", "noValue");
        if (type.equals("rider")) {

            if (id == R.id.M_mytrip) {
                Intent intent = new Intent(this, TripsActivity.class);
                startActivity(intent);
                finish();
            } else if (id == R.id.M_Trip) {
                Intent intent = new Intent(this, AllTripsActivity.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_setting) {
                Intent intent = new Intent(this, Setting.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_help) {


            } else if (id == R.id.M_mywallet) {
                setTitle("My wallet");
                Mywallet_f fa = new Mywallet_f();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fram, fa, "Mywallet");
                ft.commit();


            } else if (id == R.id.M_post) {
                Intent intent = new Intent(this, PostActivity.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_be_driver) {
                Intent intent = new Intent(this, ToBeDriverActivity.class);
                startActivity(intent);
                finish();
            }

        }
      else {
            if (id == R.id.M_mytrip) {
                Intent intent = new Intent(this, TripsActivity.class);
                startActivity(intent);
                finish();
            } else if (id == R.id.M_Trip) {
                Intent intent = new Intent(this, AllTripsActivity.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_setting) {
                Intent intent = new Intent(this, Setting.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_help) {


            } else if (id == R.id.M_mywallet) {
                setTitle("My wallet");
                Mywallet_f fa = new Mywallet_f();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fram, fa, "Mywallet");
                ft.commit();


            } else if (id == R.id.M_post) {
                Intent intent = new Intent(this, PostActivity.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.M_be_driver) {
                Intent intent = new Intent(this, DriverActivity.class);
                startActivity(intent);
                finish();
            }
                /*This drawer list can use fragment to show its options*/
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.M_be_driver).setVisible(false);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
