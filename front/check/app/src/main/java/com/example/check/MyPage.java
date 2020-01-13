package com.example.check;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printMyPage();
    }




    public void printMyPage(){
        setContentView(R.layout.user_mypage);
    }
}
