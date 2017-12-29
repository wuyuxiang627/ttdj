package com.connxun.ttdj.ui.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<BaseFragmentV4> mFragmentList      = new ArrayList<BaseFragmentV4>();
    private final List<String>         mFragmentTitleList = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public BaseFragmentV4 getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(BaseFragmentV4 fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}