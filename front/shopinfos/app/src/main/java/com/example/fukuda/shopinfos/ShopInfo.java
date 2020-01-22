package com.example.fukuda.shopinfos;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import android.net.Uri;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import java.net.MalformedURLException;
import java.net.URL;

public class ShopInfo extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener {

    private String shopId;
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
        setContentView(R.layout.shopinfo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        shopId = intent.getStringExtra("Id");
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbartab = (Toolbar)findViewById(R.id.toolbartab);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        pageAdapter=new PageAdapter((getSupportFragmentManager()));
        pageAdapter.addFragment(new TopFragment(),"Top");
        pageAdapter.addFragment(new TopFragment(),"Picture");
        pageAdapter.addFragment(new TopFragment(),"review");

        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch(tab.getPosition()) {
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

        try {
            /* 非同期通信処理の呼び出し */
            new ShopHttpResponse(this).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9"+"&id="+shopId));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
