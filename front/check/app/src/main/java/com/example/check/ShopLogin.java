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
import com.example.check.ShopSubmit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//＠＠＠＠＠＠＠店舗ログイン＠＠＠＠＠＠＠＠
public class ShopLogin extends AppCompatActivity {
    private static String URL_REGIST ="http://52.199.105.121/shop_login.php";
    //固定値とEdittextの入力内容との照合でログインできるかをテスト
    final String SHOPID = "12345678";
    final String SHOPPASS = "987654321";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_login);

        //**********ログインボタンが押された時の処理は以下の通りです*************→→→
        Button shoplogin = (Button)findViewById(R.id.buttonShopLogin);
        shoplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText idtext = (EditText) findViewById(R.id.editShopID);
                final EditText passtext = (EditText) findViewById(R.id.editShopPass);
                String shopID = idtext.getText().toString();     // 店舗ID入力文字の取得
                String shopPassword = passtext.getText().toString();//店舗パスワード入力文字列の取得

                if (shopID.length() == 0 || shopPassword.length() == 0) {
                    //店舗IDかパスワードの欄どちらかが未入力があるか
                    Toast.makeText(ShopLogin.this, "未入力の項目があります", Toast.LENGTH_LONG).show();
                }else if(shopPassword.length() <= 7) {
                    //入力された店舗IDとパスワードと取得した店舗IDとパスワードの両方が正しいかどうか
                    //どちらも正しければ店舗トップページへと遷移
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("ダイアログ")
                            .setMessage("パスワードが短すぎます")
                            .setPositiveButton("OK", null)
                            .show();

                }else {
                    sendinfo(view, shopID, shopPassword);
                   // Toast.makeText(ShopLogin.this, "入力内容が正しくありません", Toast.LENGTH_LONG).show();
                }
            }
        });
        //←←←←←←ここまで

        TextView newshop = (TextView) findViewById(R.id.textNewshop);
        //「userSelect」が押された時の処理は以下の通りです→→→→→→
        newshop.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopLogin.this, ShopSubmit.class);
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
                                Toast.makeText(ShopLogin.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ShopLogin.this, ShopSubmit.class);
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
                        Toast.makeText(ShopLogin.this,"Register Error!" + error.toString(),Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("shopid", shopid);
                params.put("password", shoppass);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}
