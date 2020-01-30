package com.example.check;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateCategory extends AppCompatActivity {
    //店舗ID
    private static String SHOPID = "";
    private static String URL_Cate = "http:/52.199.105.121/InsertCategory.php";
    private static String username = "";
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_category);

        //アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        MyApp myApp = (MyApp)this.getApplication();
        username = myApp.getTestString();
        Intent intent = getIntent();
        SHOPID = intent.getStringExtra("Id");

        //送信ボタン
        Button btn_inquiry_sub = findViewById(R.id.btn_sub);

        //username取得
        //MyApp myApp = (MyApp)this.getApplication();
        //String username = myApp.getTestString();

        final EditText cate = findViewById(R.id.cate);
        final EditText cate_kana = findViewById(R.id.cate_kana);

        btn_inquiry_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力文字
                String Cate = cate.getText().toString();
                String Cate_kana = cate_kana.getText().toString();
                int text_count = Cate.length();
                if (text_count == 0){
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("エラー")
                            .setMessage("カテゴリ名が入力されていません")
                            .setPositiveButton("OK", null)
                            .show();}
                if (text_count != 0 && text_count <= 20) {
                    post(Cate,Cate_kana,username,SHOPID,v);
                    //finish();
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("ごめんなさい...")
                            .setMessage("できれば20文字以内でお願いします")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }


        });

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


    //カテゴリを作成
    public void post(final String Cate,final String cate_kana,final String username, final String shopid, final View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Cate,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("作成完了")
                                        .setMessage("新しいカテゴリを作成しました")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            //OKを押すと元の画面に戻る
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .show();
                            }else{
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("エラー")
                                        .setMessage("「"+Cate + "」\nはすでに存在します")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(CreateCategory.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("categoryname", Cate);
                params.put("name_kana",cate_kana);
                params.put("shopid", shopid);
                params.put("username",username);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
