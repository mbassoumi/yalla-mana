package com.example.graduation.yallamana.presenation.mytrips;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.graduation.yallamana.util.network.api.Trip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mais on 7/27/2017.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Trip> today,future,past,requests;

    public HomeViewPagerAdapter(FragmentManager fm, List<Trip>today,List<Trip> future,List<Trip> past,List<Trip> requests) {
        super(fm);
        this.today=today;
        this.future=future;
        this.past=past;
        this.requests=requests;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:


                TodayFragment todays = new TodayFragment();
                Bundle b = new Bundle();
                b.putParcelableArrayList("todays", (ArrayList<? extends Parcelable>) today);
                todays.setArguments(b);

                        todays.setArguments(b);

                return todays;
            case 1:

               FutureFragment futures = new FutureFragment();

                Bundle b3 = new Bundle();

                b3.putParcelableArrayList("futures", (ArrayList<? extends Parcelable>) future);
                futures.setArguments(b3);
                return futures;


            case 2:
                PastFragment pasts = new PastFragment();

                Bundle b2 = new Bundle();
                b2.putParcelableArrayList("pasts", (ArrayList<? extends Parcelable>) past);
               /* b2.putSerializable("todays", (Serializable) past); */
                pasts.setArguments(b2);
                return pasts;
            case 3:
                RequestFragment  request= new RequestFragment();

                Bundle b5 = new Bundle();
                b5.putParcelableArrayList("requests", (ArrayList<? extends Parcelable>)requests);
               /* b2.putSerializable("todays", (Serializable) past); */
                request.setArguments(b5);
                return request;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 4;
    }

}
