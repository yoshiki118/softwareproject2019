package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//＠＠＠＠＠＠＠＠店舗ホーム画面＠＠＠＠＠＠＠＠＠
public class ShopHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_home);
        //**********初期カテゴリ編集への遷移*************
        Button buttoncategory = (Button)findViewById(R.id.buttoncategory);
        //「buttoncategory」が押された時の処理は以下の通りです→→→→→→
        buttoncategory.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopHome.this, ShopCategory.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************ユーザカテゴリ編集への遷移************
        //「buttonuser」が押された時の処理は以下の通りです→→→→→→
        Button buttonuser = (Button)findViewById(R.id.buttonuser);
        buttonuser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopHome.this, ShopUser.class);
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
                Intent intent = new Intent(ShopHome.this, ShopNoitice.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで
    }
}
