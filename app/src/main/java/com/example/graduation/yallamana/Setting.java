package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {
    private  Toolbar tool ;
    private Button btnEdit,btnFeedback,btnRating,btnSginout,btnNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tool=(Toolbar)findViewById(R.id.toolbar_setting);
        setSupportActionBar(tool);
        getSupportActionBar().setTitle(R.string.action_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Dawer_List.class));
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t= new Intent(getApplicationContext(),Edit_account.class);
                finish();
                startActivity(t);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.list) {
            Intent t = new Intent(getApplicationContext(),Dawer_List.class);
            startActivity(t);
            finish();
            return true;
        }
        else  if (id == R.id.offer_trip_m){
            Intent t = new Intent(getApplicationContext(),offer_trip.class);
            startActivity(t);
            finish();
            return true;
        }

        else  if (id == R.id.request_trip_m){
            Intent t = new Intent(getApplicationContext(),Request_trip.class);
            startActivity(t);
            finish();
            return true;
        }

        else  if (id == R.id.setting_m){
            Intent t = new Intent(getApplicationContext(),Setting.class);
            startActivity(t);
            finish();
            return true;
        }
        else  if (id == R.id.edit_accunt_m){
            Intent t = new Intent(getApplicationContext(),Edit_account.class);
            startActivity(t);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
