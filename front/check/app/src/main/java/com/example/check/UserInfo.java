package com.example.check;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.user_info);
    }
}