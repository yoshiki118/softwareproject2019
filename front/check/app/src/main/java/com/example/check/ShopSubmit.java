package com.example.check;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class ShopSubmit extends AppCompatActivity {
    private EditText shopIDText;
    private EditText shopPassText;
    private EditText shopPassCheckText;
    private TextView shopIDLabel;
    private TextView shopPassLabel;
    private TextView shopPassCheckLabel;
    private String notequal;
    private ProgressBar loading;
    private static String URL_REGIST ="http://52.199.105.121/shop_register.php";
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_submit);
        //printScreen();
        shopIDText = (EditText) findViewById(R.id.shopIDText);
        shopPassText = (EditText) findViewById(R.id.shopPassText);
        shopPassCheckText = (EditText) findViewById(R.id.shopPassCheckText);

        button = (Button) findViewById(R.id.shopSubmitButton);
        Button cbutton = (Button) findViewById(R.id.shopCancelButton);

        shopIDLabel = (TextView) findViewById(R.id.shopIDLabel);
        shopPassLabel = (TextView) findViewById(R.id.shopPassLabel);
        shopPassCheckLabel = (TextView) findViewById(R.id.shopPassCheckLabel);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String shopID = shopIDText.getText().toString();
                final String shopPass = shopPassText.getText().toString();
                final String shopPassCheck = shopPassCheckText.getText().toString();
                final String shopIDlabel = shopPassCheckLabel.getText().toString();
                final String shoppasslabel = shopPassLabel.getText().toString();
                final String shoppasschecklabel = shopPassCheckLabel.getText().toString();



                errorCheck(v,shopID, shopPass, shopPassCheck, shopIDlabel, shoppasslabel, shoppasschecklabel);

            }
        });
    }
    public void printScreen(){
        setContentView(R.layout.shop_submit);
    }

    public void errorCheck(View v, String shopID, String shopPass, String shopPassCheck, String shopIDlabel, String shoppasslabel, String shoppasschecklabel){
        int shopIDflag = 0;
        int shoppassflag = 0;
        int shoppasscheckflag = 0;
        int equalflag = 0;
//エラーチェックの処理を記述する
        if(shopPass.length() >= 8){
            if (shopPass.equals(shopPassCheck)) {
                shoppassflag = 1;
            }
            else{
                new AlertDialog.Builder(v.getContext())
                        .setTitle("ダイアログ")
                        .setMessage("パスワードが一致しません")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
        else{
            new AlertDialog.Builder(v.getContext())
                    .setTitle("ダイアログ")
                    .setMessage("パスワードが短すぎます")
                    .setPositiveButton("OK", null)
                    .show();
        }

        if(shoppassflag == 1){
            sendinfo(shopID, shopPass);
        }

    }
    public void sendinfo(final String shopid,final String shoppass){


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    Toast.makeText(ShopSubmit.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                   // nextPage();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(ShopSubmit.this,"Register Error!" + e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ShopSubmit.this,"Register Error!" + error.toString(),Toast.LENGTH_SHORT).show();
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
