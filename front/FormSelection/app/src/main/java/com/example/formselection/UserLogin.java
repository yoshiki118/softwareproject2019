package com.example.formselection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//＠＠＠＠＠＠＠ユーザログイン＠＠＠＠＠＠＠＠
public class UserLogin extends AppCompatActivity {

    //固定値とEdittextの入力内容との照合でログインできるかをテスト
    final String USERID = "abcdefg";
    final String USERPASS = "123456789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shop_notice.xmlのファイルを呼び出す
        setContentView(R.layout.user_login);

        //**********ログインボタンが押された時の処理は以下の通りです*************→→→
        Button userlogin = (Button)findViewById(R.id.buttonUserLogin);
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText idtext = (EditText) findViewById(R.id.editUserID);
                final EditText passtext = (EditText) findViewById(R.id.editUserPass);
                String userID = idtext.getText().toString();     // ユーザID入力文字の取得
                String userPassword = passtext.getText().toString();//ユーザパスワード入力文字列の取得

                if (userID.length() == 0 || userPassword.length() == 0) {
                    //ユーザIDかパスワードの欄どちらかが未入力があるか
                    Toast.makeText(UserLogin.this, "未入力の項目があります", Toast.LENGTH_LONG).show();
                }else if(userID.equals(USERID) && userPassword.equals(USERPASS)){
                    //入力されたユーザIDとパスワードと取得したユーザIDとパスワードの両方が正しいかどうか
                    Intent intent = new Intent(UserLogin.this, UserTop.class);
                    //どちらも正しければユーザトップページへと遷移
                    startActivity(intent);
                }else {
                    Toast.makeText(UserLogin.this, "入力内容が正しくありません", Toast.LENGTH_LONG).show();
                }
            }
        });
        //←←←←←←ここまで

        TextView newuser = (TextView) findViewById(R.id.textNewuser);
        //「userSelect」が押された時の処理は以下の通りです→→→→→→
        newuser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this, NewUser.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
}
