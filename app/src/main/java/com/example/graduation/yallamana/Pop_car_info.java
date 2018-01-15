package com.example.graduation.yallamana;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 10/19/17.
 */

public class Pop_car_info extends DialogFragment implements View.OnClickListener {

     View form ;
    TextView txtseatnum, txtnum, txtmodel;
    TextView txtcolor, btn_canecl ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content, Bundle b){
      AppCompatActivity  myActivity = (AppCompatActivity) getContext();
//        SharedPreferences sharedPreferences =myActivity. getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
//
//        String model =  sharedPreferences.getString("type", "noValue");
//        String year = sharedPreferences.getString("year", "noValue");


       form = inflater.inflate(R.layout.pop_car_info,content,false);
//         txtseatnum= (TextView) form.findViewById(R.id.pop_car_seat_num);
//        txtnum= (TextView) form.findViewById(R.id.year);
//        txtmodel= (TextView) form.findViewById(R.id.model);
//        txtmodel.setText(model);
//        txtnum.setText(year);

         btn_canecl = (Button) form.findViewById(R.id.cancel);

         btn_canecl.setOnClickListener(this);

        getDialog().setTitle("Car Information");

        return  form ;
    }



    @Override
    public void onClick(View view) {
        Button b = (Button) view ;
        this.dismiss();

    }
}

