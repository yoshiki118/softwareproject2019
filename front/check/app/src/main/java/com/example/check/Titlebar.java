package com.example.check;

import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class Titlebar extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タイトルバーのカスタマイズを設定可能にする
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.user_top);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
