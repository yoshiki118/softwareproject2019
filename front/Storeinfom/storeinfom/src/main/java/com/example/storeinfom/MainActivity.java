package com.example.storeinfom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements RedFragment.OnFragmentInteractionListener ,GreenFragment.OnFragmentInteractionListener ,BlueFragment.OnFragmentInteractionListener{

    Toolbar toolbar,toolbartab;
    ViewPager viewPager;
    TabLayout tabLayout;

    PageAdapter pageAdapter;
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbartab = (Toolbar)findViewById(R.id.toolbartab);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        pageAdapter=new PageAdapter((getSupportFragmentManager()));
        pageAdapter.addFragment(new RedFragment(),"Top");
        pageAdapter.addFragment(new GreenFragment(),"Picture");
        pageAdapter.addFragment(new BlueFragment(),"review");

        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch(tab.getPosition())
                {
                    case 0:
                        getWindow().setStatusBarColor(0xD0FFFF);
                        toolbar.setBackgroundColor(0xD0FFFF);
                        toolbartab.setBackgroundColor(0xD0FFFF);
                        tabLayout.setBackgroundColor(0xD0FFFF);
                        break;

                    case 1:
                        getWindow().setStatusBarColor(0xD0FFFF);
                        toolbar.setBackgroundColor(0xD0FFFF);
                        toolbartab.setBackgroundColor(0xD0FFFF);
                        tabLayout.setBackgroundColor(0xD0FFFF);
                        break;

                    case 2:
                        getWindow().setStatusBarColor(0xD0FFFF);
                        toolbar.setBackgroundColor(0xD0FFFF);
                        toolbartab.setBackgroundColor(0xD0FFFF);
                        tabLayout.setBackgroundColor(0xD0FFFF);
                        break;


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
