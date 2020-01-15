package com.example.test;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.net.URL;
import java.net.MalformedURLException;


public class ShopInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //　店舗ID
        String a = "&id=g249265";

        try {
            /* 非同期通信処理の呼び出し */
            new ShopHttpResponse(this).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9" + a));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
