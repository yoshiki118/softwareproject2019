package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ShopNoitice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sample_sample.xmlのファイルを呼び出す
        setContentView(R.layout.shop_notice);
    }
}
