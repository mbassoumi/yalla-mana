package com.example.graduation.yallamana.presenation.post.utils;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.graduation.yallamana.presenation.mytrips.TodayFragment;
import com.example.graduation.yallamana.presenation.post.fragments.DashboardFragment;
import com.example.graduation.yallamana.presenation.post.fragments.HomeFragment;
import com.example.graduation.yallamana.presenation.post.fragments.NotificationFragment;
import com.example.graduation.yallamana.util.network.api.Post;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mais on 7/27/2017.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Post>mypost;
    List<Post>allposts;


    public HomeViewPagerAdapter(FragmentManager fm, List<Post> mypost, List<Post>allposts) {
        super(fm);
        this.allposts=allposts;
        this.mypost=mypost;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:


                HomeFragment home = new HomeFragment();

                Bundle b = new Bundle();
                b.putParcelableArrayList("my", (ArrayList<? extends Parcelable>) mypost);
               home.setArguments(b);

                return home;
            case 1:

                DashboardFragment publicc = new DashboardFragment();
                Bundle b2 = new Bundle();
                b2.putParcelableArrayList("all", (ArrayList<? extends Parcelable>)allposts);
                publicc.setArguments(b2);
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
