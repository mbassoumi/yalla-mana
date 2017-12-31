package com.example.graduation.yallamana.presenation.alltrips;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.graduation.yallamana.Trip_information;
import com.example.graduation.yallamana.util.network.api.Trip;

import com.example.graduation.yallamana.R;

import java.util.List;


public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trip> tripList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to,date,price;
        public ImageView driverImage, dots;

        public MyViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            to = (TextView) view.findViewById(R.id.to);
            date = (TextView) view.findViewById(R.id.date);
            driverImage = (ImageView) view.findViewById(R.id.thumbnail);
            dots = (ImageView) view.findViewById(R.id.overflow);
            price = (TextView) view.findViewById(R.id.price);
        }
    }


    public TripsAdapter(Context mContext, List<Trip> tripList) {
        this.mContext = mContext;
        this.tripList = tripList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.from.setText(trip.getFrom());
        holder.to.setText(trip.getTo() );
        holder.date.setText(trip.getDate() );
        holder.price.setText(trip.getPrice());

        // loading trip cover using Glide library
       Glide.with(mContext).load(trip.getThumbnail()).into(holder.driverImage);

        holder.dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.dots);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_trip, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }
////////////////
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_more_information:
                    Intent intent = new Intent(mContext,Trip_information.class);
                    mContext.startActivity(intent);

                    return true;

                case R.id.action_reserve_trip:
                    Toast.makeText(mContext, "Reserve Trip", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}
