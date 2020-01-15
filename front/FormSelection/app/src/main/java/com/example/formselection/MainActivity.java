package com.example.formselection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(MainActivity.this, FormSelection.class);
        Intent intent = new Intent(MainActivity.this, FormSelection.class);
        startActivity(intent);
    }
}
