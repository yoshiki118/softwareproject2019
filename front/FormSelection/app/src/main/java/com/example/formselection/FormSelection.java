package com.example.formselection;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
//＠＠＠＠＠＠＠形態選択＠＠＠＠＠＠＠＠
public class FormSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shop_notice.xmlのファイルを呼び出す
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