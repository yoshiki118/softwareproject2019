package com.example.check;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserSubmit extends AppCompatActivity {
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
    private TextView areaLabel;
    private TextView sexLabel;
    private String notequal;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printUserSubmit();
        //accountNameTextの設定
        accountNameText =(EditText)findViewById(R.id.accountNameText);
        userPassText = (EditText)findViewById(R.id.userPassText);
        userPassCheckText = (EditText)findViewById(R.id.userPassCheckText);
        mailText = (EditText)findViewById(R.id.mailText);
        ageText = (EditText)findViewById(R.id.ageText);

        Button button = (Button) findViewById(R.id.submitButton);

        accountNameLabel = (TextView) findViewById(R.id.accountNameLabel);
        userPassLabel = (TextView) findViewById(R.id.userPassLabel);
        userPassCheckLabel = (TextView) findViewById(R.id.userPassCheckLabel);
        mailLabel = (TextView) findViewById(R.id.mailLabel);
        ageLabel = (TextView) findViewById(R.id.ageLabel);
        areaLabel =    (TextView)findViewById(R.id.areaLabel);
        sexLabel = (TextView)findViewById(R.id.areaLabel);

        //以下データ




        notequal = "パスワードが一致しません";
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               final String accounttext = accountNameText.getText().toString();
               final String Passtext = userPassText.getText().toString();
               final String PassChecktext = userPassCheckText.getText().toString();
               final String agetext = ageText.getText().toString();
               final String accountlabel = accountNameLabel.getText().toString();
               final String passlabel = userPassLabel.getText().toString();
               final String passchecklabel = userPassCheckLabel.getText().toString();
               final String agelabel = ageLabel.getText().toString();
               final String arealabel = areaLabel.getText().toString();
               String sexlabel = sexLabel.getText().toString();





                //地域選択のspinnerの値を取得
                Spinner areaspinner = (Spinner)findViewById(R.id.areaspinner);
                final String area = (String)areaspinner.getSelectedItem();
                final View ex = v;

                if(accounttext.length() != 0){
                    //パスワードが入力されているか
                    if(Passtext.length() != 0){
                        //パスワード(確認用が入力されているか)
                        if(PassChecktext.length() != 0){
                            //パスワードとパスワード(確認用が入力されているか)
                            if (Passtext.equals(PassChecktext) && Passtext.length() != 0) {
                                if(agetext.length() != 0) {
                                    if (area.length() != 0) {

                                                    printLengthError(ex, accounttext, Passtext, PassChecktext, agetext);



                                    }
                                    else{
                                        printMissError(v, arealabel);
                                    }
                                }
                                else{
                                    printNullError(v, agelabel);
                                }
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
    public void printUserSubmit(){
        setContentView(R.layout.user_submit);
    }

    public void nextPage(){
        Intent userTop= new Intent(UserSubmit.this, UserTop.class);
        startActivity(userTop);
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
    public void printMissError(View v, String error){
        String message = error + "が選択されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show();
    }

    public void printLengthError(View v, String account, String pass, String passc, String age) {
        //アカウント名が8文字以内か
        if (account.length() <= 8) {

            //パスワードが8～20文字以内か
            if (pass.length() >= 8) {
                nextPage();
            }
            //パスワードの文字数エラー
            else {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("ダイアログ")
                        .setMessage("パスワードが短すぎます")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
        //アカウント名の文字数エラー
        else {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("ダイアログ")
                    .setMessage("アカウント名は8文字以内で入力してください")
                    .setPositiveButton("OK", null)
                    .show();
        }

        int iage = Integer.parseInt(age);
        if(iage > 150 && iage < 0){
            new AlertDialog.Builder(v.getContext())
                    .setTitle("ダイアログ")
                    .setMessage("年齢が正しくありません")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
    public class areaSpinnerSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position,long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
            if(!str.equals("")){
                new AlertDialog.Builder(view.getContext())
                        .setTitle("ダイアログ")
                        .setMessage("地域を選択してください")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {
        }
    }
}
