package com.joma.quizapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.joma.quizapp.R;

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
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
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
}
