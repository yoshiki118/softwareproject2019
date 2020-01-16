package com.example.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UserTop extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Intent searchShop = new Intent(UserTop.this, SearchShop.class);
        startActivity(searchShop);
    }
    //ユーザトップ画面の処理を呼び出す
    public void homePage(){
        Intent home = new Intent(UserTop.this, UserTop.class);
        startActivity(home);
    }
    //検索画面の処理を呼び出す
    public void searchPage(){
        Intent search = new Intent(UserTop.this, SearchShop.class);
        startActivity(search);
    }
    //マイページ画面の処理を呼び出す
    public void myPage(){
        Intent myPage = new Intent(UserTop.this, MyPage.class);
        startActivity(myPage);

    }
    //戻るボタンの実装
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
