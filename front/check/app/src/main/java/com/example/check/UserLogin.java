package com.example.check;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

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
    private static String URL_REGIST ="http://52.199.105.121/user_login.php";
    //固定値とEdittextの入力内容との照合でログインできるかをテスト
    final String USERID = "abcdefg";
    final String USERPASS = "123456789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shop_notice.xmlのファイルを呼び出す
        setContentView(R.layout.user_login);

        //**********ログインボタンが押された時の処理は以下の通りです*************→→→
        Button userlogin = (Button)findViewById(R.id.buttonUserLogin);
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText idtext = (EditText) findViewById(R.id.editUserID);
                final EditText passtext = (EditText) findViewById(R.id.editUserPass);
                String userID = idtext.getText().toString();     // ユーザID入力文字の取得
                String userPassword = passtext.getText().toString();//ユーザパスワード入力文字列の取得

                if (userID.length() == 0 || userPassword.length() == 0) {
                    //ユーザIDかパスワードの欄どちらかが未入力があるか
                    Toast.makeText(UserLogin.this, "未入力の項目があります", Toast.LENGTH_LONG).show();

                }else {
                    sendinfo(view, userID, userPassword);
                    }
            }
        });
        //←←←←←←ここまで

        TextView newuser = (TextView) findViewById(R.id.textNewuser);
        //「userSelect」が押された時の処理は以下の通りです→→→→→→
        newuser.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this, UserSubmit.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
    public void sendinfo(final View v,final String shopid,final String shoppass){
        final boolean log = false;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserLogin.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserLogin.this, UserTop.class);
                                startActivity(intent);

                                // nextPage();
                            }
                            else{
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("ダイアログ")
                                        .setMessage("登録されたアカウントが見つかりません")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            new AlertDialog.Builder(v.getContext())
                                    .setTitle("ダイアログ")
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

                params.put("username", shopid);
                params.put("password", shoppass);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}