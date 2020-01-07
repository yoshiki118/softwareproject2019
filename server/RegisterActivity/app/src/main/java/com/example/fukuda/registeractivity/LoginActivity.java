package com.example.fukuda.registeractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar loading; //進捗状況表示のための変数
    private static String URL_LOGIN = "http://52.199.105.121/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        link_regist = findViewById(R.id.link_regist);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim(); //emailの欄に入力された内容を取得
                String mPass = password.getText().toString().trim();//passwordの欄に入力された内容の取得

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail, mPass); 
                } else {//入力欄に何も入力されていない場合
                    email.setError("Please insert email");
                    password.setError("Please insert password");
                }

            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//新規登録ボタン押下
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


    }
    private void Login(final String email, final String password) {//引数は入力された文字列

        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, 
                new Response.Listener<String>() { //URL_LOGINで指定したurlに接続開始
                    @Override
                    public void onResponse(String response) {
                        try { //レスポンスをjson形式で受け取り"success"に対応する文字をsuccessに代入
                              //[{"success":"1","name":"入力した名前" ...}]のような形式で返却されていると思います
                            JSONObject jsonObject = new JSONObject(response); 
                            String success = jsonObject.getString("success"); 
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) { //認証に成功するとHomeActivityへ遷移
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
				    Toast.makeText(LoginActivity.this,
					"Success Login. \nYour Name:"
					+name+"\nYour Email : "
					+email,Toast.LENGTH_SHORT)
					.show();
				}
                                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    loading.setVisibility(View.GONE);
                                
                            }

                        } catch (JSONException e) { //エラー内容をToastで表示
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() { //エラー内容をToastで表示
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //サーバに送信する文字列を設定
                Map<String, String> params = new HashMap<>(); 
                //Mapにデータを格納 
                params.put("email", email); //左に格納するのはサーバ側で認識される文字列
                params.put("password", password);//右に格納するのはxmlで設定しているid
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
