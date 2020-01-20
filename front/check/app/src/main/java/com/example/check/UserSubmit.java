package com.example.check;

import androidx.appcompat.app.ActionBar;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class UserSubmit extends AppCompatActivity {
    private EditText accountNameText;
    private EditText userPassText;
    private EditText userPassCheckText;
    private EditText mailText;
    private EditText ageText;
    private TextView accountNameLabel;
    private TextView userPassLabel;
    private TextView userPassCheckLabel;
    private TextView mailLabel;
    private TextView ageLabel;
    private TextView areaLabel;
    private TextView sexLabel;
    private String notequal;
    private ProgressBar loading;
    private static String URL_REGIST ="http://52.199.105.121/register.php";
    private Button button;
    private String sextext = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printUserSubmit();
        //accountNameTextの設定
        accountNameText =(EditText)findViewById(R.id.accountNameText);
        userPassText = (EditText)findViewById(R.id.userPassText);
        userPassCheckText = (EditText)findViewById(R.id.userPassCheckText);
        mailText = (EditText)findViewById(R.id.mailText);
        ageText = (EditText)findViewById(R.id.ageText);

        button = (Button) findViewById(R.id.submitButton);
        Button cbutton = (Button) findViewById(R.id.cancelbutton);

        accountNameLabel = (TextView) findViewById(R.id.accountNameLabel);
        userPassLabel = (TextView) findViewById(R.id.userPassLabel);
        userPassCheckLabel = (TextView) findViewById(R.id.userPassCheckLabel);
        mailLabel = (TextView) findViewById(R.id.mailLabel);
        ageLabel = (TextView) findViewById(R.id.ageLabel);
        areaLabel =    (TextView)findViewById(R.id.areaLabel);
        sexLabel = (TextView)findViewById(R.id.sexLabel);
//アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //以下データ
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex);
        // radioGroupの選択値が変更された時の処理を設定
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                // checkedIdには選択された項目のidがわたってくるので、そのidのRadioButtonを取得
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);

                // 表示する文字列を選択値によって変える
                switch (checkedId){
                    case R.id.man:
                        sextext = "男性";
                        break;
                    case R.id.woman:
                        sextext = "女性";
                        break;

                }

            }
        });
        
        notequal = "パスワードが一致しません";
        cbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.user_login);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               final String accounttext = accountNameText.getText().toString();
               final String Passtext = userPassText.getText().toString();
               final String PassChecktext = userPassCheckText.getText().toString();
               final String agetext = ageText.getText().toString();
               final String accountlabel = accountNameLabel.getText().toString();
               final String passlabel = userPassLabel.getText().toString();
               final String passchecklabel = userPassCheckLabel.getText().toString();
               final String agelabel = ageLabel.getText().toString();
               final String arealabel = areaLabel.getText().toString();
               final String sexlabel = sexLabel.getText().toString();
               int accountflag = 0;
               int passflag = 0;
               int passcheckflag = 0;
               int equalflag = 0;
               int ageflag = 0;
               int sexflag = 0;
               int areaflag = 0;

           //地域選択のspinnerの値を取得
                Spinner areaspinner = (Spinner)findViewById(R.id.areaspinner);
                final String area = (String)areaspinner.getSelectedItem();
                final View ex = v;
                if(sextext.length() != 0) {
                    sexflag = 1;
                }
                else{
                    //性別未選択エラー
                    printMissError(v,sexlabel);
                }
                //地域が選択されているか
                if (area.length() != 0) {
                    areaflag = 1;
                }
                else {
                    //地域未選択エラー
                    printMissError(v, arealabel);
                }
                if(agetext.length() != 0) {
                    int iage = Integer.parseInt(agetext);
                    if(iage < 150 && iage > 0) {
                        ageflag = 1;
                    }
                    else {
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("年齢が正しくありません")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
                else{
                    //年齢nullエラー
                    printNullError(v, agelabel);
                }


                //パスワード(確認用が入力されているか)
                if(PassChecktext.length() != 0) {
                    passcheckflag = 1;
                }
                else {
                    //パスワード確認用名nullエラー
                    printNullError(v, passchecklabel);
                }
                //パスワードが入力されているか
                if(Passtext.length() != 0) {
                    if (Passtext.length() >= 8) {
                        passflag = 1;
                    }
                    else{
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("パスワードが短すぎます")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
                else {
                    //パスワードnullエラー
                    printNullError(v,passlabel);
                }
                //パスワードとパスワードが一致するか
                if (Passtext.equals(PassChecktext)) {
                    equalflag = 1;
                }
                else if(passflag == passcheckflag){
                    //パスワードと確認用の不一致エラー
                    printNotEqualError(v);
                }
                if(accounttext.length() != 0) {
                     if (accounttext.length() <= 8) {
                         accountflag = 1;
                     }
                     else{
                        //アカウント名の文字数エラー
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("アカウント名は8文字以内で入力してください")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
                else{
                    //アカウント名nullエラー

                    printNullError(v,accountlabel);
                }
                //入力内容すべてが問題なければ次のエラーチェックへ
                if (accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && ageflag == 1 && sexflag == 1 && areaflag == 1){
                    Regist(accounttext, Passtext, agetext, sextext, area);
                    nextPage();
                }
            }
        });

}
    //戻るボタンの機能
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void printUserSubmit(){
        setContentView(R.layout.user_submit);
    }

    public void nextPage(){
        Intent userTop= new Intent(UserSubmit.this, UserTop.class);
        startActivity(userTop);
    }

    public void printNotEqualError(View v){
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(notequal)
                .setPositiveButton("OK",null)
                .show();
    }
    public void printNullError(View v, String error){
        String message = error + "が入力されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show();
    }
    public void printMissError(View v, String error){
        String message = error + "が選択されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show();
    }


    public class areaSpinnerSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position,long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
            if(!str.equals("")){
                new AlertDialog.Builder(view.getContext())
                        .setTitle("ダイアログ")
                        .setMessage("地域を選択してください")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {
        }
    }


    private void Regist(String accounttext, String pass, final String agetext, final String areatext, String sextext) {

        final String name = accounttext;
        final String password = pass;
        final String age = agetext;
        final String sex = sextext;
        final String area = areatext;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserSubmit.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserSubmit.this,"Register Error!" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserSubmit.this,"Register Error!" + error.toString(),Toast.LENGTH_SHORT).show();
                    //    loading.setVisibility(View.GONE);
                    //    button.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("password", password);
                params.put("age", age2);
                params.put("sex", sex);
                params.put("area", areatext);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
