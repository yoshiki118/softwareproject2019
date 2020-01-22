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

public class MyPage extends AppCompatActivity {
    private String MYPAGE;

    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent = new Intent(MyPage.this, UserTop.class);
            intent.putExtra("USERTOP",MYPAGE);
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        //受け取る
        Intent intent = getIntent();
        MYPAGE = intent.getStringExtra("MYPAGE");

        //アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //************情報編集への遷移**************
        Button buttonuserinfo = (Button) findViewById(R.id.useredit);
        //「userinfo」が押された時の処理は以下の通りです→→→→→→
        buttonuserinfo.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, UserEdit.class);
                intent.putExtra("USEREDIT",MYPAGE);
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
                Intent intent = new Intent(MyPage.this, InquiryActivity.class);
                intent.putExtra("INQUIRYACTIVITY",MYPAGE);
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
                intent.putExtra("PAIDCHANGE",MYPAGE);
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
                intent.putExtra("WITHDRAW",MYPAGE);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }

    //戻るボタンの実装
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(MyPage.this, UserTop.class);
                intent.putExtra("USERTOP",MYPAGE);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
