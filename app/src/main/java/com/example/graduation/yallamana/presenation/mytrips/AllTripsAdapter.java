package com.example.graduation.yallamana.presenation.mytrips;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.CardView;
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
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Date;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.api.Tripe;

import com.example.graduation.yallamana.R;

import java.util.List;


public class AllTripsAdapter extends RecyclerView.Adapter<AllTripsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trip> tripeList;
    String fromC, toC, time,priceT,status;
    double price;
    Cities from,to;
    Date dateTrip;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to,date,price;
        public ImageView driverImage, dots;
        public CardView userCard;

        public MyViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.city_from);
            to = (TextView) view.findViewById(R.id.city_to);
            date = (TextView) view.findViewById(R.id.date);
            driverImage = (ImageView) view.findViewById(R.id.profile_image);
        //    dots = (ImageView) view.findViewById(R.id.overflow);
            userCard =(CardView)view.findViewById(R.id.card_view);

        }
    }


    public AllTripsAdapter(Context mContext, List<Trip> tripeList,String status) {
        this.mContext = mContext;
        this.tripeList = tripeList;
        this.status=status;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        from = tripeList.get(position).getStartPoint();
        to = tripeList.get(position).getEndPoint();
        fromC = from.getName().getEn().toString();
        toC = to.getName().getEn().toString();
        dateTrip = tripeList.get(position).getDate();
        time = dateTrip.getDate() + " " + dateTrip.getTime();

        Trip tripe = tripeList.get(position);
        holder.from.setText(fromC);
        holder.to.setText(toC);
        holder.date.setText(time);

        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Trip_information.class);
                intent.putExtra("tripId",tripeList.get(position).getId());
                intent.putExtra("tripsStatus",status);

                mContext.startActivity(intent);


            }
        });
        // loading tripe cover using Glide library
        Glide.with(mContext).load(R.drawable.person2).into(holder.driverImage);

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
        inflater.inflate(R.menu.menu_mytrip, popup.getMenu());
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

                case R.id.action_cancle_trip:
                    Toast.makeText(mContext, "Cnclle", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        if (tripeList==null){
            return 0;
        }
        return tripeList.size();
    }

}
