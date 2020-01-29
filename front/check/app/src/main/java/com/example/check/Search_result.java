package com.example.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.check.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import java.util.ArrayList;

public class Search_result extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //検索パラメータ
    private String params;
    List<ShopList> shopList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //検索画面からパラメータを受け取りparamsに格納
        Intent intent = getIntent();
        params = intent.getStringExtra(Rest_searchActivity.EXTRA_DATA);
        Toast.makeText(Search_result.this,params,Toast.LENGTH_LONG).show();
        ListView listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);


        try {
            ShopAdapter adapter = new ShopAdapter(this);

            /* 非同期通信処理の呼び出し */
            new HttpResponse(this, adapter, listView, shopList).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1"+ "&hit_per_page=20"+params));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(),ShopInfo.class);

        String selectedId = shopList.get(position).getId();

        intent.putExtra("Id", selectedId);

        // shopinfoへ遷移
        startActivity(intent);

    }
}

