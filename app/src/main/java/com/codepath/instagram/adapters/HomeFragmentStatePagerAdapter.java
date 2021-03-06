package com.codepath.instagram.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.codepath.instagram.R;
import com.codepath.instagram.fragments.PostsFragment;
import com.codepath.instagram.helpers.SmartFragmentStatePagerAdapter;

/**
 * Created by lshi on 12/4/15.
 */
public class HomeFragmentStatePagerAdapter extends SmartFragmentStatePagerAdapter {
    private Context mContext;

    private static int NUM_ITEMS = 5;

    private int[] imageResId = {
        R.drawable.ic_home,
        R.drawable.ic_search,
        R.drawable.ic_capture,
        R.drawable.ic_notifs,
        R.drawable.ic_profile,
    };

    public HomeFragmentStatePagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
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
                return PostsFragment.newInstance();
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = ContextCompat.getDrawable(mContext, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
