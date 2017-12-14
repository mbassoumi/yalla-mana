package com.example.graduation.yallamana.presenation.post.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.graduation.yallamana.presenation.post.fragments.DashboardFragment;
import com.example.graduation.yallamana.presenation.post.fragments.HomeFragment;
import com.example.graduation.yallamana.presenation.post.fragments.NotificationFragment;


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


                HomeFragment home = new HomeFragment();

                return home;
            case 1:

                DashboardFragment publicc = new DashboardFragment();
                return publicc;


            case 2:

                NotificationFragment notifications = new NotificationFragment();
                return notifications;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 3;
    }

}
