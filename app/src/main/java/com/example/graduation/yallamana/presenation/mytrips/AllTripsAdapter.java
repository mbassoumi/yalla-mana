package com.example.graduation.yallamana.presenation.mytrips;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.Trip_information;
import com.example.graduation.yallamana.util.network.api.Tripe;

import java.util.List;


public class AllTripsAdapter extends RecyclerView.Adapter<AllTripsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Tripe> tripeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView from, to,date;
//        public ImageView driverImage, dots;

        public MyViewHolder(View view) {
            super(view);
//            from = (TextView) view.findViewById(R.id.from);
//            to = (TextView) view.findViewById(R.id.to);
//            date = (TextView) view.findViewById(R.id.date);
//            driverImage = (ImageView) view.findViewById(R.id.driverImage);
//            dots = (ImageView) view.findViewById(R.id.dots);
        }
    }


    public AllTripsAdapter(Context mContext, List<Tripe> tripeList) {
        this.mContext = mContext;
        this.tripeList = tripeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        Tripe trip = tripeList.get(position);
//        holder.from.setText(trip.getFrom());
//        holder.to.setText(trip.getTo() );
//        holder.date.setText(trip.getDate() );

        // loading trip cover using Glide library
       //Glide.with(mContext).load(trip.getThumbnail()).into(holder.driverImage);

//        holder.dots.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.dots);
//            }
//        });
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
                    Toast.makeText(mContext, "Cancle Tripe", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return tripeList.size();
    }
}
