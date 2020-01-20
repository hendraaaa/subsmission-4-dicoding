package com.example.subs4;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.subs4.fragment.MovieFav;
import com.example.subs4.fragment.MovieFragment;
import com.example.subs4.fragment.TvShowFav;
import com.example.subs4.fragment.TvShowFragment;

public class pagerFavoriteAdapter extends FragmentPagerAdapter {

    private Context mcontext;
    public pagerFavoriteAdapter(Context context, FragmentManager fm){
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFav();
                break;
            case 1:
                fragment = new TvShowFav();
                break;
        }
        return fragment;
    }

    /* @Nullable
     @Override
     public CharSequence getPageTitle(int position){
         return titleList.get(position);
     }

     @Override
     public int getCount() {
         return titleList.size();
     }
     public void addTitle(int title){
         titleList.add(Integer.toString(title));
     }*/
    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.text1,
            R.string.text2
    };

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(TAB_TITLES[position]);
    }
}
