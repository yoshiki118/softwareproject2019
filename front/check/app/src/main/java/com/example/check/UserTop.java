package com.example.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UserTop extends AppCompatActivity {
    private String USERTOP;

    // 端末の戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent = new Intent(UserTop.this, UserLogin.class);
            intent.putExtra("USERLOGIN",USERTOP);
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ログインされたIDを前の画面から受け取る
        Intent intent = getIntent();
        USERTOP = intent.getStringExtra("USERTOP");

        //アクションバーに戻るボタンの追加
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        printUserTop();
        //検索ボタンの準備
        Button searchbutton = (Button)findViewById(R.id.searchbutton);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                nextPage();
            }
        });
        //ホームアイコンの準備
        ImageButton homeIcon = (ImageButton)findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                homePage();
            }
        });
        //マイページボタンの準備
        Button mypagebutton = (Button)findViewById(R.id.mypagebutton);
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                myPage();
            }
        });
        //検索アイコンの準備
        ImageButton searchIcon = (ImageButton)findViewById(R.id.searchIcon);
        searchIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                searchPage();
            }
        });
        //マイページアイコンの準備
        ImageButton myPageIcon = (ImageButton)findViewById(R.id.myPageIcon);
        myPageIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                myPage();
            }
        });

    }


    //ユーザトップ画面の表示
    public void printUserTop(){
        setContentView(R.layout.user_top);
    }

    //次のページの処理を呼び出す
    public void nextPage(){
        Intent intent = new Intent(UserTop.this, SearchShop.class);
        intent.putExtra("SEARCHSHOP",USERTOP);      //ログインされたIDを次の画面に渡す
        startActivity(intent);
    }
    //ユーザトップ画面の処理を呼び出す
    public void homePage(){
        Intent home = new Intent(UserTop.this, UserTop.class);
        home.putExtra("USERTOP",USERTOP);       //ログインされたIDを次の画面に渡す
        startActivity(home);
    }
    //検索画面の処理を呼び出す
    public void searchPage(){
        Intent intent = new Intent(UserTop.this, SearchShop.class);
        intent.putExtra("SEARCHSHOP",USERTOP);      //ログインされたIDを次の画面に渡す
        startActivity(intent);
    }
    //マイページ画面の処理を呼び出す
    public void myPage(){
        Intent myPage = new Intent(UserTop.this, MyPage.class);
        myPage.putExtra("MYPAGE",USERTOP);      //ログインされたIDを次の画面に渡す
        startActivity(myPage);
    }
    //アクションバー戻るボタンの処理
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(UserTop.this, UserLogin.class);
                intent.putExtra("USERLOGIN",USERTOP);       //ログインされたIDを前の画面に渡す
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
