package com.example.fukuda.shopinfos.ui.main;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fukuda.shopinfos.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private static final String[] TAB_TITLES = new String[]{"TOP", "IMAGE", "REVIEW"};
    private final Activity mContext;
    private String id;

    public SectionsPagerAdapter(Activity context, FragmentManager fm, String id) {
        super(fm);
        mContext = context;
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, this.id, mContext);
    }

    //タブの名前を取得するget
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    //タブの個数を取得するget
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }

    public String getId() {
        return this.id;
    }
}