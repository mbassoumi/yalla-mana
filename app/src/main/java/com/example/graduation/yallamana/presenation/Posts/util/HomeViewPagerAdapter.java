package ps.exalt.facebook.presenation.home.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ps.exalt.facebook.presenation.home.fragments.FriendFragment;
import ps.exalt.facebook.presenation.home.fragments.NotificationFragment;
import ps.exalt.facebook.presenation.home.fragments.PostFragment;

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
                PostFragment posts = new PostFragment();

                return posts;
            case 1:

                FriendFragment friends = new FriendFragment();
                return friends;

            case 2:
                PostFragment posts2 = new PostFragment();

                return posts2;
            case 3:

                FriendFragment friends2 = new FriendFragment();
                return friends2;
            case 4:

                NotificationFragment notifications2 = new NotificationFragment();
                return notifications2;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 5;
    }

}
