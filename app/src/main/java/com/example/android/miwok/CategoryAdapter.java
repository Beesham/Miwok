package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Beesham on 9/9/2016.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new NumbersFragment();

            case 1:
                return new FamilyFragment();

            case 2:
                return new ColorsFragment();

            case 3:
                return new PhrasesFragment();
        }
        return  null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return mContext.getString(R.string.category_numbers);

            case 1:
                return mContext.getString(R.string.category_family);

            case 2:
                return mContext.getString(R.string.category_colors);

            case 3:
                return mContext.getString(R.string.category_phrases);
        }
        return  null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
