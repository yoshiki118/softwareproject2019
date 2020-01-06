package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.Window;
import android.content.Intent;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserSubmit extends Activity{
   private EditText accountNameText;
    private EditText userPassText;
    private EditText userPassCheckText;
    private EditText mailText;
    private EditText ageText;
    private TextView accountNameLabel;
    private TextView userPassLabel;
    private TextView userPassCheckLabel;
    private TextView mailLabel;
    private TextView ageLabel;
    private String notequal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userSubmit();
        //accountNameTextの設定
        accountNameText =(EditText)findViewById(R.id.accountNameText);
        userPassText = (EditText)findViewById(R.id.userPassText);
        userPassCheckText = (EditText)findViewById(R.id.userPassCheckText);
        mailText = (EditText)findViewById(R.id.mailText);
        ageText = (EditText)findViewById(R.id.ageText);
        //submitButtonボタンの設定
       Button button = (Button) findViewById(R.id.submitButton);
       //accountNameLabelの設定
        accountNameLabel = (TextView) findViewById(R.id.accountNameLabel);
        userPassLabel = (TextView) findViewById(R.id.userPassLabel);
        userPassCheckLabel = (TextView) findViewById(R.id.userPassCheckLabel);
        mailLabel = (TextView) findViewById(R.id.mailLabel);
        ageLabel = (TextView) findViewById(R.id.ageLabel);
        notequal = "パスワードが一致しません";

        button.setOnClickListener(new View.OnClickListener() {
            //ボタンが押された時の処理
            public void onClick(View v) {

                //アカウント名とパスワードの取得
                String accounttext = accountNameText.getText().toString();
                String Passtext = userPassText.getText().toString();
                String PassChecktext = userPassCheckText.getText().toString();
                String agetext = ageText.getText().toString();
                String accountlabel = accountNameLabel.getText().toString();
                String passlabel = userPassLabel.getText().toString();
                String passchecklabel = userPassCheckLabel.getText().toString();
                String agelabel = ageLabel.getText().toString();




                //テキストの表示
                //Toast.makeText(userPassLabel.getContext(), accounttext, Toast.LENGTH_LONG).show();
                //表示をするダイアログの設定

//インテント生成(画面遷移に必要)
                //アカウント名が入力されているか
                if(accounttext.length() != 0){
                    //パスワードが入力されているか
                    if(Passtext.length() != 0){
                        //パスワード(確認用が入力されているか)
                        if(PassChecktext.length() != 0){
                            //パスワードとパスワード(確認用が入力されているか)
                            if (Passtext.equals(PassChecktext) && Passtext.length() != 0) {
                                printLengthError(v, accounttext, Passtext, PassChecktext,agetext);
                            }
                            //パスワードと確認用の不一致エラー
                            else{
                                printNotEqualError(v);
                            }
                        }
                        //パスワード(確認用nullエラー)
                        else{
                            printNullError(v, passchecklabel);
                        }
                    }
                    //パスワードnullエラー
                    else{
                        printNullError(v,passlabel);
                    }
                }
                //アカウント名nullエラー
                else {
                    printNullError(v,accountlabel);
                }



            }
        });
    }
      public void userSubmit(){
        setContentView(R.layout.user_submit);

    }
    public void nextPage(){
        Intent intent = new Intent(UserSubmit.this, UserTop.class);
        //設定したActivity(インテント生成時に設定したもの->UserTop)を開始
        startActivity(intent);
    }
    public void printNotEqualError(View v){
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(notequal)
                .setPositiveButton("OK",null)
                .show();
    }
    public void printNullError(View v, String error){
        String message = error + "が入力されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show();
    }

    public void printLengthError(View v, String account, String pass, String passc, String age){
        //アカウント名が8文字以内か
        if(account.length() <= 8){

            //パスワードが8～20文字以内か
            if(pass.length() >= 8){
                nextPage();
            }
            //パスワードの文字数エラー
            else{
                new AlertDialog.Builder(v.getContext())
                        .setTitle("ダイアログ")
                        .setMessage("パスワードが短すぎます")
                        .setPositiveButton("OK",null)
                        .show();
            }
        }
        //アカウント名の文字数エラー
        else{
            new AlertDialog.Builder(v.getContext())
                    .setTitle("ダイアログ")
                    .setMessage("アカウント名は8文字以内で入力してください")
                    .setPositiveButton("OK",null)
                    .show();
        }


    }

}
