package com.example.graduation.yallamana.presenation.mytrips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.Trip_information;
import com.example.graduation.yallamana.presenation.alltrips.TripsAdapter;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Date;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.api.User;

import java.util.List;

import static com.example.graduation.yallamana.R.*;


public class TripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;
    private Activity activity;
    private List<Trip> trips;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private Context mContext;
    private List<Trip> tripeList;
    String fromC, toC, time,priceT,status;
    double price;
    Cities from,to;
    Date dateTrip;


    public TripAdapter(RecyclerView recyclerView, List<Trip> trips, Activity activity,String status) {
        this.trips = trips;
        this.activity = activity;
        this.status=status;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return trips.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(layout.trip_row, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       int position1=position+1;
        if (holder instanceof UserViewHolder) {
            Trip trip = trips.get(position);
            final UserViewHolder userViewHolder = (UserViewHolder) holder;

            from = trip.getStartPoint();
            to = trip.getEndPoint();
            fromC = from.getName().getEn().toString();
             toC = to.getName().getEn().toString();
             dateTrip = trip.getDate();
            time = dateTrip.getDate() + " " + dateTrip.getTime();

            userViewHolder.from.setText(fromC);
            userViewHolder.to.setText(toC);
            userViewHolder.date.setText(time);

            userViewHolder.userCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity myActivity = (AppCompatActivity) v.getContext();

                    Intent intent = new Intent(myActivity,Trip_information.class);
                    intent.putExtra("tripId",trip.getId());
                    intent.putExtra("tripsStatus",status);

                    myActivity.startActivity(intent);


                }
            });
            // loading tripe cover using Glide library
         //  Glide.with(mContext).load(R.drawable.person2).into(userViewHolder.driverImage);
        }
    }

    @Override
    public int getItemCount() {
        if(trips==null){
            return 0;
        }
        return trips.size();
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(menu.menu_trip, popup.getMenu());
        popup.setOnMenuItemClickListener(new TripAdapter.MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case id.action_more_information:
                    Toast.makeText(mContext, "More Info", Toast.LENGTH_SHORT).show();
                    return true;

                case id.action_reserve_trip:
                    Toast.makeText(mContext, "Reserve Tripe", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }



    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to, date, price;
        public ImageView driverImage, dots;
        public CardView userCard;

        public UserViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.city_from);
            to = (TextView) view.findViewById(R.id.city_to);
            date = (TextView) view.findViewById(R.id.date);
            driverImage = (ImageView) view.findViewById(R.id.profile_image);
            //  dots = (ImageView) view.findViewById(R.id.overflow);
            userCard = (CardView) view.findViewById(R.id.card_view);

        }
    }
}