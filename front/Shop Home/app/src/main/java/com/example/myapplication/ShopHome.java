package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//＠＠＠＠＠＠＠＠AndroidManifest.xmlにしっかり記述しろよ!!!!＠＠＠＠＠＠＠＠

public class ShopHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_home);
    //Button型の「buttoncategory」はID『buttoncategory』ですよ
        Button buttoncategory = (Button)findViewById(R.id.buttoncategory);
        //「buttoncategory」が押された時の処理は以下の通りです→→→→→→
        buttoncategory.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
//                Toast.makeText(ShopHome.this,"クリックされたよ", Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(ShopHome.this, ShopSample.class);
                ～.thisの～の部分が現在のJavaファイル名
                ＿.classの＿の部分が遷移したいファイル名
                →ここで画面遷移をするっていうとる
                */
                Intent intent = new Intent(ShopHome.this, ShopSample.class);
                //飛ばすで～
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //「buttonyouser」が押された時の処理は以下の通りです→→→→→→
        Button buttonyouser = (Button)findViewById(R.id.buttonyouser);
        buttonyouser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopHome.this, ShopYouser.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

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
