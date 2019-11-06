package com.joma.quizapp.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.joma.quizapp.history.HistoryFragment;
import com.joma.quizapp.settings.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return HistoryFragment.newInstance();
            case 2:
                return SettingsFragment.newInstance();
            default:
                return MainFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
