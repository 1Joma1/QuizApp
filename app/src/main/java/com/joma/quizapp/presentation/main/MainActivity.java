package com.joma.quizapp.presentation.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.joma.quizapp.R;
import com.joma.quizapp.core.EnglishToRussianFirebaseTranslator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));

        init();
    }

    private void init() {
        viewPagerInitWithBottomNav();
    }

    private void viewPagerInitWithBottomNav(){
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_main:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_history:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.menu_settings:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_main);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_history);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_settings);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem()==0){
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(0, false);
        }
    }
}
