package com.example.graduation.yallamana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Edit_account extends AppCompatActivity {
    private Toolbar tool ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        tool= (Toolbar)findViewById(R.id.toolbar);
        tool.setTitle("Edit Account");
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        tool.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);



        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_account.this,Setting.class);
                startActivity(intent);
                finish();
            }
        });
    }
//////////////////


}
