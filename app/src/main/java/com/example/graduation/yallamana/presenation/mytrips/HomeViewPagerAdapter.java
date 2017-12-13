package com.example.graduation.yallamana.presenation.mytrips;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by Mais on 7/27/2017.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {


    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:


                TodayFragment today = new TodayFragment();

                return today;
            case 1:

               FutureFragment future = new FutureFragment();
                return future;


            case 2:
                PastFragment past = new PastFragment();
                return past;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 3;
    }

}
