package com.example.formselection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.new_user);
    }
}