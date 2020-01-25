package com.example.check;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//＠＠＠＠＠＠＠形態選択＠＠＠＠＠＠＠＠
public class FormSelection extends AppCompatActivity {

    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //形態選択.xmlのファイルを呼び出す
        setContentView(R.layout.form_selection);

        //**********ユーザログインへの遷移*************
        Button userSelect = (Button)findViewById(R.id.userSelect);
        //「userSelect」が押された時の処理は以下の通りです→→→→→→
        userSelect.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormSelection.this, UserLogin.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //**********店舗ログインへの遷移*************
        Button shopSelect = (Button)findViewById(R.id.shopSelect);
        //「shopSelect」が押された時の処理は以下の通りです→→→→→→
        shopSelect.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormSelection.this, ShopLogin.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
    
}