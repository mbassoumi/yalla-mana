package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.graduation.yallamana.presenation.post.PostActivity;

import java.util.ArrayList;

public class Setting extends AppCompatActivity {
    private Toolbar tool;
    private Button btnEdit, btnFeedback, btnRating, btnSginout, btnNote;
    String[] notificationArray = {"Edit Account", "Feedback", "Rating app", "Notifications"};


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tool = (Toolbar) findViewById(R.id.toolbar_setting);
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        tool.setTitle("Settings");
        tool.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Drawer_List.class);
                startActivity(intent);
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.setting_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<SettingType> getDataSet() {
        ArrayList results = new ArrayList<SettingType>();
        for (int index = 0; index < notificationArray.length; index++) {
            SettingType obj = new SettingType(notificationArray[index].toString());
            results.add(index, obj);
        }
        return results;
    }
}