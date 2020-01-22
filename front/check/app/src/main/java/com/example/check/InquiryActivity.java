package com.example.check;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InquiryActivity extends AppCompatActivity implements TextWatcher {
    private InputMethodManager inputMethodManager;
    private String INQUIRYACTIVITY;
    ConstraintLayout mainLayout;
    EditText editText;
    TextView textView;
    String item = null;

    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent = new Intent(InquiryActivity.this, MyPage.class);
            intent.putExtra("MYPAGE",INQUIRYACTIVITY);
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        //受け取る
        Intent intent = getIntent();
        INQUIRYACTIVITY = intent.getStringExtra("INQUIRYACTIVITY");

        //アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //送信ボタン
        Button btn_inquiry_sub = findViewById(R.id.btn_inquiry_sub);
        TextView textView_inquiry = findViewById(R.id.textview_inquiry);

        // テキストを設定
        textView_inquiry.setText(R.string.text_inquiry);

        //キーボードを閉じたいEditTextオブジェクト
        editText  = (EditText) findViewById(R.id.inquiry_contents);
        //画面全体のレイアウト
        mainLayout = findViewById(R.id.inquiryLayout);
        //キーボード表示を制御するためのオブジェクト
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        // リスナーを登録
        editText.addTextChangedListener(this);
        textView = findViewById(R.id.alert_text);

        //ArrayAdapterインスタンスの作成
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.inquiry_title,R.layout.support_simple_spinner_dropdown_item);
        //リストに表示するためのレイアウトリソースを設定
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //スピナーの作成
        Spinner spinner = (Spinner)findViewById(R.id.spinner_inquiry);
        //アダプタ設定
        spinner.setAdapter(adapter);
        //選択した項目の取得
        //item = (String)spinner.getSelectedItem();

        btn_inquiry_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力文字
                String text = editText.getText().toString();
                int text_count = text.length();
                if (text_count == 0)
                    Toast.makeText(InquiryActivity.this, "お問い合わせ内容が入力されていません", Toast.LENGTH_SHORT).show();
                if (text_count <= 10) {
                    Toast.makeText(InquiryActivity.this, "送信しました。\nお問い合わせありがとうございます。", Toast.LENGTH_SHORT).show();
                    editText.getEditableText().clear();
                } else {
                    Toast.makeText(InquiryActivity.this, "お問い合わせ内容は10文字以内でお願いします。", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
        // テキスト変更後に変更されたテキストを取り出す
        String inputStr = s.toString();

        // 文字長をカウントして280文字を超えると「オーバー」とする
        if(inputStr.length() > 10){
            String str = " 文字数オーバー";
            textView.setText(str);
            textView.setTextColor(Color.RED);
        }else{
            textView.setText("");
        }


    }

    //EditText編集時に背景をタップしたらキーボードを閉じるようにするタッチイベントの処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mainLayout.requestFocus();

        return false;
    }

    //戻るボタンの実装
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(InquiryActivity.this, MyPage.class);
                intent.putExtra("MYPAGE",INQUIRYACTIVITY);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
