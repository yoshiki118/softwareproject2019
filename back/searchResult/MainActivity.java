package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.net.URL;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            /* アダプターの作成 */
            ShopAdapter adapter = new ShopAdapter(this);
            /* 非同期通信処理HttpResponseの呼び出し */
            new HttpResponse(this, adapter).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&name=居酒屋"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
