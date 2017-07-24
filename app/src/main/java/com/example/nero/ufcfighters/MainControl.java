package com.example.nero.ufcfighters;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.nero.ufcfighters.Fragments.AllFighters_Fragment;
import com.example.nero.ufcfighters.Fragments.Titleholder_Fragment;

public class MainControl extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control);

        toolbar = (Toolbar)findViewById(R.id.mainCtrl_toolbar);
        tabLayout = (TabLayout)findViewById(R.id.mainCtrl_tabLayout);
        viewPager = (ViewPager)findViewById(R.id.mainCtrl_viewPager);

        setSupportActionBar(toolbar);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Titleholder_Fragment(), "Title Holders");
        viewPagerAdapter.addFragments(new AllFighters_Fragment(), "All Fighters");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
