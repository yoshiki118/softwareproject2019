package com.example.check;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

public class EditReview extends AppCompatActivity {

    private static final String SHOPID = "gc0a608";
    private static String URL_PostInfo = "http:/52.199.105.121/InsertReview.php";
    private String username;
    private String shopid;



    private InputMethodManager inputMethodManager;
    ConstraintLayout mainLayout;
    EditText editText;
    String item = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_review);

        Intent intent = getIntent();
        shopid = intent.getStringExtra("shopid");

        MyApp myApp = (MyApp)this.getApplication();
        username = myApp.getTestString();
        Toast.makeText(EditReview.this, username, Toast.LENGTH_SHORT).show();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //送信ボタン
        Button btn_inquiry_sub = findViewById(R.id.btn_sub);

        //キーボードを閉じたいEditTextオブジェクト
        editText = findViewById(R.id.contents);
        //画面全体のレイアウト
        mainLayout = findViewById(R.id.edit_info);
        //キーボード表示を制御するためのオブジェクト
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        btn_inquiry_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力文字
                String text = editText.getText().toString();

                int text_count = text.length();
                if (text_count == 0)
                    Toast.makeText(EditReview.this, "レビューが入力されていません", Toast.LENGTH_SHORT).show();
                else if (text_count <= 500) {
                    post(text, shopid, username);
                    finish();
                } else {
                    Toast.makeText(EditReview.this, "レビューは500文字以内でお願いします。", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }


    //reviewを投稿
    public void post(final String contents, final String shopid, final String username) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PostInfo,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                                Toast.makeText(EditReview.this, "投稿が完了しました。", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(EditReview.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("username", username);
                params.put("shopid", shopid);
                params.put("reviewcontents", contents);
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



}
