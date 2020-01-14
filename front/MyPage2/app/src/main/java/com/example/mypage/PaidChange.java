package com.example.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PaidChange extends AppCompatActivity {
    //radiobuttonが何も押されていないかを判定するための変数
    private int change = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.paid_change);

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
                    Toast.makeText(PaidChange.this, "登録完了", Toast.LENGTH_LONG).show();
                } else if (change == 2) {
                    //クレジット決済のラジオボタンが選択されている
                    final EditText cardText = (EditText) findViewById(R.id.editCard);
                    final EditText cordText = (EditText) findViewById(R.id.editCord);
                    String card = cardText.getText().toString();     // 入力文字の取得
                    String cord = cordText.getText().toString();
                    //カード番号とセキュリティコードの欄両方に入力があるか
                    if (card.length() != 0 && cord.length() != 0) {
                        Toast.makeText(PaidChange.this, "登録完了", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}
