package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;




public class Trip_information extends AppCompatActivity {

    private Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_information);
        tool=(Toolbar)findViewById(R.id.info_toolbar);
        setSupportActionBar(tool);

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu,m);
        return  true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.M_mytrip){}
        else if(id == R.id.M_mywallet){}
        else if (id == R.id.M_Trip){}
        else if (id == R.id.M_post){}
        else if (id == R.id.M_setting){}
        else if (id == R.id.M_help){}
        return  super.onOptionsItemSelected(item);
    }
}
