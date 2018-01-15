package com.example.graduation.yallamana;

/**
 * Created by m_7el on 12/15/2017.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduation.yallamana.presenation.alltrips.AllTripsActivity;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<SettingType> mDataset;
    private static MyClickListener myClickListener;



    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView settingType;
        private Context context;


        public DataObjectHolder(View itemView) {
            super(itemView);
            settingType = (TextView) itemView.findViewById(R.id.setting_type);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity myActivity = (AppCompatActivity) v.getContext();


            if (settingType.getText().equals("Edit Account")) {
                Intent intent = new Intent(myActivity,EditAccountActivity.class);
//                intent.putExtra("hi","setting");
                myActivity.startActivity(intent);

            } else if (settingType.getText().equals("Notification")) {
//                Intent intent = new Intent(context,Drawer_List.class);
//                context.startActivity(intent);

            } else if (settingType.getText().equals("Rating App")) {
                Intent intent = new Intent(context,Drawer_List.class);
               context.startActivity(intent);

            } else if (settingType.getText().equals("Feedback")) {
                Intent intent = new Intent(context,Drawer_List.class);
                context.startActivity(intent);

            }
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<SettingType> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.setting_card, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.settingType.setText(mDataset.get(position).getSettingType());

    }

    public void addItem(SettingType dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}