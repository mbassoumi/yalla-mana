package com.example.graduation.yallamana.presenation.mytrips;

import android.app.Activity;
import android.content.Context;
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


import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.presenation.alltrips.TripsAdapter;
import com.example.graduation.yallamana.util.network.api.User;

import java.util.List;

import static com.example.graduation.yallamana.R.*;


public class TripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context mContext;

    private boolean isLoading;
    private Activity activity;
    private List<Trips> trips;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public TripAdapter(RecyclerView recyclerView, List<Trips> trips, Activity activity) {
        this.trips = trips;
        this.activity = activity;

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
        if (holder instanceof UserViewHolder) {
            Trips trip = trips.get(position);
            final UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.from.setText(trip.getFrom());
            userViewHolder.to.setText(trip.getTo());
            userViewHolder.date.setText(trip.getDate());
            //userViewHolder.profile.setImageDrawable(drawable.person1);
            userViewHolder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(userViewHolder.overflow);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
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
                    Toast.makeText(mContext, "Reserve Trip", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }



    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView from;
        public TextView to;
        public TextView date;
        public ImageButton profile;
        public ImageView overflow ;


        public UserViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(id.city_from);
            date = (TextView) view.findViewById(id.date);
            to = (TextView) view.findViewById(id.city_to);
            overflow = (ImageView) view.findViewById(id.overflow);
            profile = (ImageButton) view.findViewById(id.profile_image);


        }
    }
}