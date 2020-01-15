package com.example.formselection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
//＠＠＠＠＠＠＠店舗ログイン＠＠＠＠＠＠＠＠
public class ShopLogin extends AppCompatActivity {

    //固定値とEdittextの入力内容との照合でログインできるかをテスト
    final String SHOPID = "12345678";
    final String SHOPPASS = "987654321";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_login);

        //**********ログインボタンが押された時の処理は以下の通りです*************→→→
        Button shoplogin = (Button)findViewById(R.id.buttonShopLogin);
        shoplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText idtext = (EditText) findViewById(R.id.editShopID);
                final EditText passtext = (EditText) findViewById(R.id.editShopPass);
                String shopID = idtext.getText().toString();     // 店舗ID入力文字の取得
                String shopPassword = passtext.getText().toString();//店舗パスワード入力文字列の取得

                if (shopID.length() == 0 || shopPassword.length() == 0) {
                    //店舗IDかパスワードの欄どちらかが未入力があるか
                    Toast.makeText(ShopLogin.this, "未入力の項目があります", Toast.LENGTH_LONG).show();
                }else if(shopID.equals(SHOPID) && shopPassword.equals(SHOPPASS)){
                    //入力された店舗IDとパスワードと取得した店舗IDとパスワードの両方が正しいかどうか
                    Intent intent = new Intent(ShopLogin.this, UserTop.class);
                    //どちらも正しければ店舗トップページへと遷移
                    startActivity(intent);
                }else {
                    Toast.makeText(ShopLogin.this, "入力内容が正しくありません", Toast.LENGTH_LONG).show();
                }
            }
        });
        //←←←←←←ここまで

        TextView newshop = (TextView) findViewById(R.id.textNewshop);
        //「userSelect」が押された時の処理は以下の通りです→→→→→→
        newshop.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopLogin.this, NewShop.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
}
