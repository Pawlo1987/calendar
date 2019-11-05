package com.example.user.calendarexemp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] data = new String[42];
    String[] data1 = new String[42];
    String[] data2 = new String[42];
    ViewPager vpMain;
    PagerAdapter pagerAdapter;
    TextView tvMonthTitle;
    ImageButton imageButton;
    ImageButton imageButton2;
    String[] monthName = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};

    static final int PAGE_COUNT = 12;
    MonthFragment monthFragment;
    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonthTitle = findViewById(R.id.tvMonthTitle);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);

        //создаем экземпляр объекта MonthFragment
        monthFragment = new MonthFragment();

        vpMain = (ViewPager) findViewById(R.id.vpMain);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(pagerAdapter);

        vpMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d("MyLog", "onPageSelected, position = " + position);
                tvMonthTitle.setText(monthName[position]);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpMain.setCurrentItem(vpMain.getCurrentItem()-1);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpMain.setCurrentItem(vpMain.getCurrentItem()+1);
            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public MonthFragment getItem(int position) {

            return monthFragment.newInstance( position, data, data1, data2);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

}//MainActivity
