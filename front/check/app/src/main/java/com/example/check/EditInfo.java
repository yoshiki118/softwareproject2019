package com.example.check;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditInfo extends AppCompatActivity implements TextWatcher {
    //店舗ID
    private static final String SHOPID = "gc0a608";
    private static String URL_PostInfo = "http:/52.199.105.121/Edit_info.php";

    private String EDITINFO;
    private InputMethodManager inputMethodManager;
    ConstraintLayout mainLayout;
    EditText editText;
    TextView textView;
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //受け取る
        MyApp myApp = (MyApp)this.getApplication();
        EDITINFO = myApp.getTestString();

        //送信ボタン
        Button btn_inquiry_sub = findViewById(R.id.btn_sub);

        //キーボードを閉じたいEditTextオブジェクト
        editText  = (EditText) findViewById(R.id.contents);
        //画面全体のレイアウト
        mainLayout = findViewById(R.id.edit_info);
        //キーボード表示を制御するためのオブジェクト
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        // リスナーを登録
        editText.addTextChangedListener(this);
        textView = findViewById(R.id.alert_text);

        btn_inquiry_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力文字
                String text = editText.getText().toString();
                int text_count = text.length();
                if (text_count == 0){
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("エラー")
                            .setMessage("おしらせを入力してください")
                            .setPositiveButton("OK", null)
                            .show();
                }
                else if (text_count <= 200) {
                    post(text,EDITINFO);
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("登録完了")
                            .setMessage("変更しました")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                //OKを押すと元の画面に戻る
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("エラー")
                            .setMessage("お知らせは200文字以内でお願いします")
                            .setPositiveButton("OK", null)
                            .show();
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

        // 文字長をカウントして200文字を超えると「オーバー」とする
        if(inputStr.length() > 200){
            String str = " 文字数オーバー";
            textView.setText(str);
            textView.setTextColor(Color.RED);
        }else{
            textView.setText("");
        }


    }
    //お知らせを投稿
    public void post(final String contents,final String shopid) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PostInfo,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {}//Toast.makeText(EditInfo.this, "投稿が完了しました。", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(EditInfo.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("shopid", shopid);
                params.put("commentcontents",contents);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
                Intent intent = new Intent(EditInfo.this, ShopHome.class);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
