package com.example.fukuda.restsearch;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import java.net.MalformedURLException;
import java.net.URL;

public class ShopInfo extends AppCompatActivity {

    private String shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopinfo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        shopId = intent.getStringExtra("Id");
        ;

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
