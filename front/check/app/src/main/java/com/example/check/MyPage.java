package com.example.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        //************情報編集への遷移**************
        Button buttonuserinfo = (Button) findViewById(R.id.userinfo);
        //「userinfo」が押された時の処理は以下の通りです→→→→→→
        buttonuserinfo.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, UserInfo.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************ユーザの問い合わせへの遷移**************
        Button buttoninquiry = (Button) findViewById(R.id.userinquiry);
        //「userinquiry」が押された時の処理は以下の通りです→→→→→→
        buttoninquiry.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, UserInquiry.class);
                //UserInquiry.classが仮で作った問い合わせ用クラスなので置き換えてください
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************有料会員登録への遷移**************
        Button buttonpaid = (Button) findViewById(R.id.paidmember);
        //「paidmember」が押された時の処理は以下の通りです→→→→→→
        buttonpaid.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, PaidChange.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************退会画面への遷移**************
        Button withdraw = (Button) findViewById(R.id.buttonwithdraw);
        //「buttonwithdraw」が押された時の処理は以下の通りです→→→→→→
        withdraw.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, Withdraw.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
}
