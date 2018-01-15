package com.example.graduation.yallamana.presenation.alltrips;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.Trip_information;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Date;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.Trip;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trip> tripeList;
    String fromC, toC, time, priceT;
    double price;
    Cities from, to;
    int rating;
    Date dateTrip;
    RetrofitInterface retrofitInterface;
    int tripId, riderNumber;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to, date, price;
        public ImageView driverImage, dots;
        public CardView userCard;

        public RatingBar ratingBar;
        public MyViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            to = (TextView) view.findViewById(R.id.to);
            date = (TextView) view.findViewById(R.id.date);
            driverImage = (ImageView) view.findViewById(R.id.thumbnail);
            // dots = (ImageView) view.findViewById(R.id.overflow);
            price = (TextView) view.findViewById(R.id.price);
            userCard = (CardView) view.findViewById(R.id.card_view);
            ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);

            retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        }
    }


    public TripsAdapter(Context mContext, List<Trip> tripeList) {
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
        from = tripeList.get(position).getStartPoint();
        to = tripeList.get(position).getEndPoint();
        fromC = from.getName().getEn().toString();
        toC = to.getName().getEn().toString();
        dateTrip = tripeList.get(position).getDate();
        time = dateTrip.getDate() + " " + dateTrip.getTime();
        price = tripeList.get(position).getPrice();
        priceT = price + " Nis";
        if(tripeList.get(position).getRiders()!=null){
        riderNumber = tripeList.get(position).getRiders().getCount();}
        else {
            riderNumber = 0;

    }
        Trip tripe = tripeList.get(position);
        holder.from.setText(fromC);
        holder.to.setText(toC);
        holder.date.setText(time);
        holder.price.setText(priceT);
        tripId = tripeList.get(position).getId();
        int[] covers = new int[]{
                R.drawable.person1,

                R.drawable.unknown_user,
                R.drawable.person2,
                R.drawable.unknown_user,
                R.drawable.person1,
        };
        Random random=new Random();
        Random r = new Random();
        rating= r.nextInt(5-0) + 0;
        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Trip_information.class);
                intent.putExtra("tripId", tripeList.get(position).getId());
                intent.putExtra("tripsStatus", "all");
                intent.putExtra("ridersNumber", riderNumber);

                mContext.startActivity(intent);


            }
        });
       holder.ratingBar.setRating(rating);
        // loading tripe cover using Glide library
        Glide.with(mContext).load(R.drawable.unknown_user).into(holder.driverImage);

    }


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
                    Intent intent = new Intent(mContext, Trip_information.class);
                    mContext.startActivity(intent);

                    return true;

                case R.id.action_reserve_trip:
                    // Toast.makeText(mContext, "Reserve Trip", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    String token = "Bearer " + sharedPreferences.getString("token", "noValue");

                    Call<Example> call2 = retrofitInterface.reverseTrip(token, tripId);
                    call2.enqueue(new Callback<Example>() {
                        @Override
                        public void onResponse(Call<Example> call, Response<Example> response) {
                            Example example = response.body();
                            if (response.code() == 200) {
                                Toast.makeText(getApplicationContext(), example.getMessage().toString(), Toast.LENGTH_SHORT).show();

                            } else {
                                //
                            }

                        }

                        @Override
                        public void onFailure(Call<Example> call, Throwable t) {

                        }
                    });

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
