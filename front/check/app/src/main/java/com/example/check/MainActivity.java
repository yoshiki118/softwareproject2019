package com.example.check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printMainActivity();
    }
    public void printMainActivity(){
        Intent usersubmit = new Intent(MainActivity.this, SearchShop.class);
        startActivity(usersubmit);
    }
}
