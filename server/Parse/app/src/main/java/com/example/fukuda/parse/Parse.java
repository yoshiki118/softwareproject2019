package com.example.fukuda.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Parse extends AppCompatActivity {
    private static String URL = "http://52.199.105.121/SelectShopinfo.php";
    //店舗のid
    private static final String shopid = "0000000";
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse);
        //内容
        textView = findViewById(R.id.text);
        //日
        textView1 = findViewById(R.id.textView);
        //時
        textView2 = findViewById(R.id.textView2);
        Parse(shopid);
    }
    private void Parse(final String shopid) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //パース
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("info");

                            JSONObject object = jsonArray.getJSONObject(0);
                            String contents = object.getString("commentcontents").trim();
                            String date = object.getString("commentdate").trim();
                            String time = object.getString("commenttime").trim();

                            if(contents == null){
                                Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
                                finish();
                            }else if(contents.equals("")){

                                finish();
                            }

                            textView.setText(contents);
                            textView1.setText(date);
                            textView2.setText(time);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            finish();
//                            Toast.makeText(Parse.this, "Error" + e.toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finish();
                        Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopid", shopid);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
