package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class offer_trip extends AppCompatActivity {
     private Toolbar tool ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_trip);
        tool = (Toolbar) findViewById(R.id.toolbar_offer);
        setSupportActionBar(tool);
        getSupportActionBar().setTitle(R.string.Offer_trip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
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
