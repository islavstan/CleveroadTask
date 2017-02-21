package com.islavstan.cleveroadtask.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.islavstan.cleveroadtask.fragments.FavoriteFragment;
import com.islavstan.cleveroadtask.fragments.ResultFragment;

import java.util.HashMap;
import java.util.Map;


public class MyViewPagerAdapter  extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    int positionTab = 0;
    public Map<Integer, Fragment> list;
    String textSearch = "";

    public MyViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        initTabs();

    }

    @Override
    public Fragment getItem(int position) {
        if (position < 2)
            return list.get(position);
        else
            return null;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof ResultFragment && positionTab == 0) {
            ResultFragment rf = (ResultFragment) object;
            rf.update(textSearch);
        } else if (object instanceof FavoriteFragment && positionTab == 1) {
            FavoriteFragment ff = (FavoriteFragment) object;
            ff.update(textSearch);
        }

        return super.getItemPosition(object);
    }

    private void initTabs() {
        list = new HashMap<>();
        this.list.put(0, new ResultFragment());
        this.list.put(1, new FavoriteFragment());

    }

    public void refreshTab(int position, String textSearch) {
        switch (position) {
            case 0:
                ResultFragment rf = (ResultFragment) this.getItem(position);
                rf.update(textSearch);
                break;
            case 1:
                FavoriteFragment ff = (FavoriteFragment) this.getItem(position);
                ff.update(textSearch);
                break;


        }
    }

    public void refreshAdapter(int position, String textSearch) {
        this.textSearch = textSearch;
        positionTab = position;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}