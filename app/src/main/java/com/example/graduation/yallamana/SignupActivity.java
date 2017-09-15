package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar appToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
      Toolbar  mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle(R.string.new_user);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
       // case android.R.id.home:// back home
        if (item .getItemId()== R.id.backButton) {
            Toast.makeText(getApplicationContext(), "back", Toast.LENGTH_SHORT).show();
            Intent my = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(my);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

