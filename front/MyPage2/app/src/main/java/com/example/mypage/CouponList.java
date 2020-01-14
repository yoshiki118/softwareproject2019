package com.example.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CouponList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.coupon_list);
    }
}
