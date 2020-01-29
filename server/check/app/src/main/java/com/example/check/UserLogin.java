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
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.check.UserSubmit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//＠＠＠＠＠＠＠ユーザログイン＠＠＠＠＠＠＠＠
public class UserLogin extends AppCompatActivity {
    public MyApp tobasu;
    private EditText editText;
    private String USERLOGIN;
    private static String URL_REGIST ="http://52.199.105.121/user_login.php";

    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ユーザログイン.xmlのファイルを呼び出す
        setContentView(R.layout.user_login);

        //アクションバーに戻るボタンを追加
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tobasu=(MyApp)this.getApplication();

        //**********ログインボタンが押された時の処理は以下の通りです*************→→→
        Button userlogin = (Button)findViewById(R.id.buttonUserLogin);
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText idtext = (EditText) findViewById(R.id.editUserID);
                final EditText passtext = (EditText) findViewById(R.id.editUserPass);
                String userID = idtext.getText().toString();     // ユーザID入力文字の取得
                //USERLOGIN = userID;
                String userPassword = passtext.getText().toString();//ユーザパスワード入力文字列の取得

                tobasu.setTestString(userID);

                //ユーザIDかパスワードの欄どちらかが未入力があるか
                if (userID.length() == 0 || userPassword.length() == 0) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("エラー")
                            .setMessage("未入力の項目があります")
                            .setPositiveButton("OK", null)
                            .show();
                }
                //どちらの項目にも入力されているか
                else {
                    sendinfo(view, userID, userPassword);
                    }
            }
        });
        //←←←←←←ここまで

        //「新規会員登録はこちら」が押された時の処理は以下の通りです→→→→→→
        TextView newuser = (TextView) findViewById(R.id.textNewuser);
        newuser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                //ユーザ新規会員登録に飛ぶ
                Intent intent = new Intent(UserLogin.this, UserSubmit.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }

    //DBとの通信
    public void sendinfo(final View v,final String userid,final String userpass){
        final boolean log = false;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            ////////////////照合が正しく行えた時の処理を以下のif文にて記述////////////////////
                            if (success.equals("1")) {
                                //Toast.makeText(UserLogin.this, "ようこそ！！", Toast.LENGTH_SHORT).show();
                                //final EditText idtext = (EditText) findViewById(R.id.editUserID);
                                //String userID = idtext.getText().toString();     // ユーザID入力文字の取得
                                //USERLOGIN = userID;


                                Intent intent = new Intent(UserLogin.this, UserTop.class);
//                                intent.putExtra("USERTOP",USERLOGIN);
                                startActivity(intent);
                                // nextPage();
                            }
                            //入力されたアカウントとパスワードがおかしい
                            else{
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("エラー")
                                        .setMessage("登録されたアカウントが見つかりません")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            new AlertDialog.Builder(v.getContext())
                                    .setTitle("エラー")
                                    .setMessage("登録されたアカウントが見つかりません")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserLogin.this,"Register Error!" + error.toString(),Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", userid);
                params.put("password", userpass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //アクションバーの戻るボタン処理
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}