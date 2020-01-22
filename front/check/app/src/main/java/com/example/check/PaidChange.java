package com.example.check;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.DialogInterface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PaidChange extends AppCompatActivity {
    //radiobuttonが何も押されていないかを判定するための変数
    private int change = 0;
    private String PAIDCHANGE;

    //戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent = new Intent(PaidChange.this, MyPage.class);
            intent.putExtra("MYPAGE",PAIDCHANGE);
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.paid_change);

        //受け取る
        Intent intent = getIntent();
        PAIDCHANGE = intent.getStringExtra("PAIDCHANGE");

        //アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RadioButton radioCarrier = (RadioButton)findViewById(R.id.radioCarrier);
        radioCarrier.setOnClickListener(new View.OnClickListener() {
            //キャリア決済ラジオボタンが押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                change = 1;
            }
        });

        RadioButton radioCredit = (RadioButton)findViewById(R.id.radioCredit);
        radioCredit.setOnClickListener(new View.OnClickListener() {
            //クレジット決済ラジオボタンが押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                change = 2;
            }
        });

        Button buttonregister = (Button) findViewById(R.id.buttonRegister);
        buttonregister.setOnClickListener(new View.OnClickListener() {
            //登録ボタンが押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                if (change == 1) {
                    //キャリア決済のラジオボタンが選択されている
//                    Toast.makeText(PaidChange.this, "登録完了", Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("ダイアログ")
                            .setMessage("変更しました")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                //OKを押すと元の画面に戻る
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                } else if (change == 2) {
                    //クレジット決済のラジオボタンが選択されている
                    final EditText cardText = (EditText) findViewById(R.id.editCard);
                    final EditText cordText = (EditText) findViewById(R.id.editCord);
                    String card = cardText.getText().toString();     // 入力文字の取得
                    String cord = cordText.getText().toString();
                    //カード番号とセキュリティコードの欄両方に入力があるか
                    if (card.length() != 0 && cord.length() != 0) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("変更しました")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    //OKを押すと元の画面に戻る
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    }else{
                        //空欄があります
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("エラー")
                                .setMessage("入力内容に空欄があります")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }else{
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("エラー")
                            .setMessage("選択してください")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.paidCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            //キャンセルボタンが押された時の処理は以下の通りです
            @Override
            public void onClick(View view){
                Intent intent = new Intent(PaidChange.this, MyPage.class);
                intent.putExtra("MYPAGE",PAIDCHANGE);
                finish();
            }
        });
    }



    //戻るボタンの実装
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(PaidChange.this, MyPage.class);
                intent.putExtra("MYPAGE",PAIDCHANGE);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

