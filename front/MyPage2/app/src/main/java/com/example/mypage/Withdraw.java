package com.example.mypage;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.RadioButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Withdraw extends AppCompatActivity {
    //radiobuttonが何も押されていないかを判定するための変数
    private int reasonflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdraw);

        RadioButton radioDontuse = (RadioButton)findViewById(R.id.radioDontuse);
        radioDontuse.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                reasonflag = 1;
            }
        });

        RadioButton radioNoshop = (RadioButton)findViewById(R.id.radioNoshop);
        radioNoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonflag = 2;
            }
        });

        RadioButton radioButuse = (RadioButton)findViewById(R.id.radioButuse);
        radioButuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonflag = 3;
            }
        });

        RadioButton radioNoinfo = (RadioButton)findViewById(R.id.radioNoinfo);
        radioNoinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonflag = 4;
            }
        });

        RadioButton radioReason = (RadioButton)findViewById(R.id.radioReason);
        radioReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonflag = 5;
            }
        });

        Button buttonDraw = (Button) findViewById(R.id.buttonDraw);
        buttonDraw.setOnClickListener(new View.OnClickListener() {
            //退会ボタン押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {

                if (reasonflag == 5) {
                    //その他が選択されていれば
                    final EditText reasonText = (EditText) findViewById(R.id.editReason);
                    //editTextに入力があるか入力文字の取得
                    String reason = reasonText.getText().toString();
                    //editTextになにかしらが入力されていればlengthは0ではないためif文が実行される
                    if (reason.length() != 0) {
                        Toast.makeText(Withdraw.this, "ご利用いただきありがとうございました", Toast.LENGTH_LONG).show();
                    }
                } else if (reasonflag != 0) {
                    //その他以外のradioButtonが選択されていれば問答無用
                    Toast.makeText(Withdraw.this, "ご利用いただきありがとうございました", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}