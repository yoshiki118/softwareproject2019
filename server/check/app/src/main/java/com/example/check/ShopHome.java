package com.example.check;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//＠＠＠＠＠＠＠＠店舗ホーム画面＠＠＠＠＠＠＠＠＠
public class ShopHome extends AppCompatActivity {
    private String SHOPHOME;


    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent = new Intent(ShopHome.this, ShopLogin.class);
            intent.putExtra("SHOPLOGIN",SHOPHOME);
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //受け取る
        Intent intent = getIntent();
        SHOPHOME = intent.getStringExtra("SHOPHOME");

        //************ユーザカテゴリ編集への遷移************
        //「buttonuser」が押された時の処理は以下の通りです→→→→→→
        Button buttonuser = (Button)findViewById(R.id.buttonuser);
        buttonuser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopHome.this, MyCategoryview.class);
                intent.putExtra("MYCATEGORYVIEW",SHOPHOME);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //********お知らせ編集への遷移****************
        //「buttonnotice」が押された時の処理は以下の通りです→→→→→→
        Button buttonnotice = (Button)findViewById(R.id.buttonnnotice);
        buttonnotice.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ShopHome.this, ShopNoitice.class);
                Intent intent = new Intent(ShopHome.this, EditInfo.class);
                intent.putExtra("EDITINFO",SHOPHOME);
                //遷移テスト
                startActivity(intent);
            }
        });
        //←←←←←←ここまで
    }
    //戻るボタンの実装
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ShopHome.this, ShopLogin.class);
                intent.putExtra("SHOPLOGIN",SHOPHOME);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
