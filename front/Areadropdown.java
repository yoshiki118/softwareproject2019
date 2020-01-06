package com.example.test;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Areadropdown extends MainActivity{
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*spinnerのドロップダウンを出す処理*/
        setContentView(R.layout.user_fooder);
        Spinner spinner = findViewById(R.id.Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.areaspinner_dropdown,
                getResources().getStringArray(R.array.list)
        );
        adapter.setDropDownViewResource(R.layout.areaspinner_dropdown);
        spinner.setAdapter(adapter);
    }


    }
