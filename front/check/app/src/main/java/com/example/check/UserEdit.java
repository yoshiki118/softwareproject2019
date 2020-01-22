package com.example.check;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class UserEdit extends AppCompatActivity {
    private TextView accountNameText;
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
    private static String URL_REGIST = "http://52.199.105.121/UserEdit.php";
    private static String URL_NOAGE = "http://52.199.105.121/UserEditnoage.php";
    private static String URL_NOSEX = "http://52.199.105.121/UserEditnosex.php";
    private static String URL_NOAREA = "http://52.199.105.121/UserEditnoarea.php";
    private static String URL_NOSEXNOAREA = "http://52.199.105.121/UserEditnosexnoarea.php";
    private static String URL_NOAGENOAREA = "http://52.199.105.121/UserEditnoagenoarea.php";
    private static String URL_NOAGENOSEX = "http://52.199.105.121/UserEditnoagenosex.php";
    private static String URL_NOAGENOSEXNOAREA = "http://52.199.105.121/UserEditnoagenosexnoarea.php";
    private Button button;
    private String sextext = "";

//    private final String MYNAME = "12121212";
    private String USEREDIT = "254561";

    // 戻るボタンの処理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(UserEdit.this, MyPage.class);
            intent.putExtra("MYPAGE",USEREDIT);
            // 戻るボタンの処理
            finish();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //受け取る
        Intent intent = getIntent();
        USEREDIT = intent.getStringExtra("USEREDIT");

        printUserEdit();
        //accountNameTextの設定
        accountNameText = findViewById(R.id.accountNameText);
        userPassText = (EditText) findViewById(R.id.userPassText);
        userPassCheckText = (EditText) findViewById(R.id.userPassCheckText);
        mailText = (EditText) findViewById(R.id.mailText);
        ageText = (EditText) findViewById(R.id.ageText);

        button = (Button) findViewById(R.id.submitButton);
        Button cbutton = (Button) findViewById(R.id.cancelbutton);

        accountNameLabel = (TextView) findViewById(R.id.accountNameLabel);
        userPassLabel = (TextView) findViewById(R.id.userPassLabel);
        userPassCheckLabel = (TextView) findViewById(R.id.userPassCheckLabel);
        mailLabel = (TextView) findViewById(R.id.mailLabel);
        ageLabel = (TextView) findViewById(R.id.ageLabel);
        areaLabel = (TextView) findViewById(R.id.areaLabel);
        sexLabel = (TextView) findViewById(R.id.sexLabel);

//アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //アカウント名をセット
        accountNameText.setText(USEREDIT);

        //以下データ
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex);
        // radioGroupの選択値が変更された時の処理を設定
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedIdには選択された項目のidがわたってくるので、そのidのRadioButtonを取得
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);

                // 表示する文字列を選択値によって変える
                switch (checkedId) {
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
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
                Spinner areaspinner = (Spinner) findViewById(R.id.areaspinner);
                final String area = (String) areaspinner.getSelectedItem();
                final View ex = v;
                if (sextext.length() != 0) {
                    sexflag = 1;
                } else {
                    //性別未選択エラー
//                    printMissError(v, sexlabel);

                }
                //地域が選択されているか
                if (area.length() != 0) {
                    areaflag = 1;
                } else {
                    //地域未選択エラー
//                    printMissError(v, arealabel);
                }
                if (agetext.length() != 0) {
                    int iage = Integer.parseInt(agetext);
                    if (iage < 150 && iage > 0) {
                        ageflag = 1;
                    } else {
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("年齢が正しくありません")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                    //年齢nullエラー
                    //printNullError(v, agelabel);
                }


                //パスワード(確認用が入力されているか)
                if (PassChecktext.length() != 0) {
                    passcheckflag = 1;
                } else {
                    //パスワード確認用名nullエラー
                    printNullError(v, passchecklabel);
                }
                //パスワードが入力されているか
                if (Passtext.length() != 0) {
                    if (Passtext.length() >= 8) {
                        passflag = 1;
                    } else {
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("パスワードが短すぎます")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                    //パスワードnullエラー
                    printNullError(v, passlabel);
                }
                //パスワードとパスワードが一致するか
                if (Passtext.equals(PassChecktext)) {
                    equalflag = 1;
                } else if (passflag == passcheckflag) {
                    //パスワードと確認用の不一致エラー
                    printNotEqualError(v);
                }
                if (accounttext.length() != 0) {
                    if (accounttext.length() <= 8) {
                        accountflag = 1;
                    } else {
                        //アカウント名の文字数エラー
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("ダイアログ")
                                .setMessage("アカウント名は8文字以内で入力してください")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                    //アカウント名nullエラー

                    printNullError(v, accountlabel);
                }
                //入力内容すべてが問題なければ次のエラーチェックへ
                if (accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && ageflag == 1 && sexflag == 1 && areaflag == 1) {
                    EditFull(Passtext, agetext, sextext, area);
                    nextPage();
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && sexflag == 1 && areaflag == 1){
                    EditNAge(Passtext,area,sextext);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && ageflag == 1 && areaflag == 1){
                    EditNSex(Passtext, agetext, area);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && ageflag == 1 && sexflag == 1){
                    EditNArea(Passtext, agetext, sextext);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && ageflag == 1){
                    EditAge(Passtext, agetext);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && sexflag == 1){
                    EditSex(Passtext, sextext);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1 && areaflag == 1){
                    EditArea(Passtext, area);
                }else if(accountflag == 1 && passflag == 1 && passcheckflag == 1 && equalflag == 1){
                    EditPass(Passtext);
                }
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(UserEdit.this, MyPage.class);
                intent.putExtra("MYPAGE",USEREDIT);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void printUserEdit() {
        setContentView(R.layout.user_edit);
    }

    public void nextPage() {
        Intent intent = new Intent(UserEdit.this, MyPage.class);
        intent.putExtra("MYPAGE",USEREDIT);
        finish();
    }

    public void printNotEqualError(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(notequal)
                .setPositiveButton("OK", null)
                .show();
    }

    public void printNullError(View v, String error) {
        String message = error + "が入力されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    public void printMissError(View v, String error) {
        String message = error + "が選択されていません";
        new AlertDialog.Builder(v.getContext())
                .setTitle("ダイアログ")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


    public class areaSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView parent, View view, int position, long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
            if (!str.equals("")) {
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

    private void EditFull( final String pass, final String agetext, final String sextext, final String areatext) {

        //final String name = accounttext;
        final String password = pass;
        final String age = agetext;
        final String sex = sextext;
        final String area = areatext;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("age", age2);
                params.put("sex", sex);
                params.put("area", area);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditNAge(final String pass, final String areatext, final String sextext) {

        //final String name = accounttext;
        final String password = pass;
        final String sex = sextext;
        final String area = areatext;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
//                params.put("age", age2);
                params.put("password", password);
                params.put("sex", sex);
                params.put("area", area);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditNSex( final String pass, final String agetext, final String areatext) {

        //final String name = accounttext;
        final String password = pass;
        final String age = agetext;
        final String area = areatext;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOSEX,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("age", age2);
                params.put("area", area);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditNArea( final String pass, final String agetext,final String sextext) {

        //final String name = accounttext;
        final String password = pass;
        final String age = agetext;
        final String sex = sextext;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOAREA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("age", age2);
                params.put("sex", sex);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditAge( final String pass, final String agetext) {

        //final String name = accounttext;
        final String password = pass;
        final String age = agetext;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOSEXNOAREA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String age2 = String.valueOf(age);
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("age", age2);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditSex( final String pass, final String sextext) {

        //final String name = accounttext;
        final String password = pass;
        final String sex = sextext;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOAGENOAREA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("sex", sex);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditArea( final String pass,final String areatext) {

        //final String name = accounttext;
        final String password = pass;
        final String area = areatext;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOAGENOSEX,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);
                params.put("area", area);
                //params.put("mail", email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void EditPass( final String pass) {

        //final String name = accounttext;
        final String password = pass;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NOAGENOSEXNOAREA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEdit.this, "編集が完了しました", Toast.LENGTH_SHORT).show();
                                nextPage();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEdit.this, "編集に失敗しました" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEdit.this, "Edit Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //    loading.setVisibility(View.GONE);
                        //    button.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", USEREDIT );
                params.put("password", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

