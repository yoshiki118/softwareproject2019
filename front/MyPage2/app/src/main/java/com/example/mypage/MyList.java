package com.example.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //my_list.xmlのファイルを呼び出す
        setContentView(R.layout.my_list);
    }
}

