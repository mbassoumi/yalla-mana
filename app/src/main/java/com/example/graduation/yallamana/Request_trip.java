package com.example.graduation.yallamana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class Request_trip extends AppCompatActivity {
    private Toolbar tool ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_trip);
        tool = (Toolbar) findViewById(R.id.toolbar_request);
        setSupportActionBar(tool);
        getSupportActionBar().setTitle(R.string.request_toolbar);
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
