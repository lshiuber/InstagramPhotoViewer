package com.codepath.instagram.models;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.codepath.instagram.helpers.SmartFragmentStatePagerAdapter;

/**
 * Created by lshi on 12/4/15.
 */
public class HomeFragmentStatePagerAdapter extends SmartFragmentStatePagerAdapter {
    private static int NUM_ITEMS = 2;

    public HomeFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                // return PostsFragment.newInstance(0, "Page # 1");
                return PostsFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment
                // return PostsFragment.newInstance(0, "Page # 1");
                return PostsFragment.newInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Posts";
            case 1:
                return "Page " + position;
            default:
                return null;
        }
    }
}
