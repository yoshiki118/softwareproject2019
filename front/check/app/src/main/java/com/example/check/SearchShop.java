package com.example.check;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.check.Rest_searchActivity.EXTRA_DATA;

public class SearchShop extends AppCompatActivity {
    //店舗id
    private static String shopid = "gc0a608";
    //接続url
    private static String URL_CatagoryList = "http:/52.199.105.121/CategoryList.php";
    //接続url
    private static String URL_catagorylist = "http:/52.199.105.121/categorylist.php";
    //category1のアダプタ
    private ArrayAdapter<String> ad_cate1;
    //category2のアダプタ
    private ArrayAdapter<String> ad_cate2;
    //category3のアダプタ
    private ArrayAdapter<String> ad_cate3;
    private RequestQueue mQueue;

    //private

    //shopidを格納
    private ArrayList<String> al_cate1 = new ArrayList<>();
    private ArrayList<String> al_cate2 = new ArrayList<>();
    private ArrayList<String> al_cate3 = new ArrayList<>();

    //検索パラメータ
    private ArrayList<String> param1 = new ArrayList<>();
    private ArrayList<String> param2 = new ArrayList<>();
    private ArrayList<String> param3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_shop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //検索ボタン
        Button searchButton = findViewById(R.id.searchButton);

        //五十音のspinner
        Spinner sp_1 = (Spinner)findViewById(R.id.sp_1);
        //五十音のspinner
        Spinner sp_2 = (Spinner)findViewById(R.id.sp_2);
        //五十音のspinner
        Spinner sp_3 = (Spinner)findViewById(R.id.sp_3);

        //category1のspinner
        final Spinner sp_cate1 = (Spinner)findViewById(R.id.category1);
        //category2のspinner
        final Spinner sp_cate2 = (Spinner)findViewById(R.id.category2);
        //category3のspinner
        final Spinner sp_cate3 = (Spinner)findViewById(R.id.category3);

        //アダプタ
        ad_cate1 = new ArrayAdapter<String>(SearchShop.this, android.R.layout.simple_spinner_item);
        ad_cate2 = new ArrayAdapter<String>(SearchShop.this, android.R.layout.simple_spinner_item);
        ad_cate3 = new ArrayAdapter<String>(SearchShop.this, android.R.layout.simple_spinner_item);

        //五十音のリスナーを登録
        sp_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item1 = spinner.getSelectedItem().toString();


                ad_cate1.clear();
                if(position != 0 ) {
                    //データベースから選択した文字から始まるカテゴリ名を取得,spinnerにセット
                    getCategory1(item1);
                    sp_cate1.setAdapter(ad_cate1);
                    ad_cate1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ1のリスナーを登録
        sp_cate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択したカテゴリ名の取得
                String cate1 = spinner.getSelectedItem().toString();

                if(position != 0 ) {
                    //選択したカテゴリ名を付与されているshopidをarraylistに格納
                    al_cate1 = getShopid(cate1);

                    if(al_cate1 .isEmpty()){

                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //五十音のリスナーを登録
        sp_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item2 = spinner.getSelectedItem().toString();
                //Toast.makeText(SearchShop.this,item,Toast.LENGTH_SHORT).show();
                ad_cate2.clear();
                if(position != 0 ) {
                    //データベースから選択した文字から始まるカテゴリ名を取得,spinnerにセット
                    getCategory2(item2);
                    sp_cate2.setAdapter(ad_cate2);
                    ad_cate2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ2のリスナーを登録
        sp_cate2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択したカテゴリ名の取得
                String cate2 = spinner.getSelectedItem().toString();
                if(position != 0 ) {
                    //選択したカテゴリ名を付与されているshopidをarraylistに格納
                    al_cate2 = getShopid(cate2);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //五十音のリスナーを登録
        sp_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item3 = spinner.getSelectedItem().toString();
                //Toast.makeText(SearchShop.this,item,Toast.LENGTH_SHORT).show();
                ad_cate3.clear();
                if(position != 0 ) {
                    //データベースから選択した文字から始まるカテゴリ名を取得,spinnerにセット
                    getCategory3(item3);
                    sp_cate3.setAdapter(ad_cate3);
                    ad_cate3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ3のリスナーを登録
        sp_cate3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択したカテゴリ名の取得
                String cate3 = spinner.getSelectedItem().toString();
                if(position != 0 ) {
                    //選択したカテゴリ名を付与されているshopidをarraylistに格納
                    al_cate3 = getShopid(cate3);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //検索ボタンのリスナーを登録
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //&id=の出現回数
                int count_id = 0;


                //検索パラメータの作成
                StringBuilder params = new StringBuilder();


                if (!(al_cate1.isEmpty())) {
                    params.append("&id=");
                    for (int i = 0; i < al_cate1.size(); i++) {
                        //最後の一つ手前までは","を追加,要素10ごとに"&id="を追加
                        params.append(al_cate1.get(i));
                        count_id++;
                        if (i != 0 && i % 9 == 0) {
                            params.append("&id=");
                        } else if (i == al_cate1.size()-1) {

                        }else{
                            params.append(",");
                        }
                    }
                }


                if (!(al_cate1.isEmpty())) {
                    for (int i = 0; i < al_cate2.size(); i++) {
                        if(count_id==10){ break;}
                        else if(i==0){ params.append(",");}

                        //最後の一つ手前までは","を追加,要素10ごとに"&id="を追加
                        params.append(al_cate2.get(i));
                        count_id++;
                        if (i != 0 && i % 9 == 0) {
                            params.append("&id=");
                        } else if (i == al_cate2.size()-1) {

                        }else{
                            params.append(",");
                        }
                    }
                }

                if (!(al_cate3.isEmpty())) {
                    for (int i = 0; i < al_cate3.size(); i++) {
                        if(count_id==10){ break;}
                        else if(i==0){ params.append(",");}
                        //最後の一つ手前までは","を追加,要素10ごとに"&id="を追加
                        params.append(al_cate3.get(i));
                        count_id++;
                        if (i != 0 && i % 9 == 0) {
                            params.append("&id=");
                        } else if (i == al_cate3.size()-1) {

                        }else{
                            params.append(",");
                        }
                    }
                }

                Intent intent = new Intent(getApplication(), Search_result.class);
                intent.putExtra("str", String.valueOf(params));
                //検索結果画面に遷移
                startActivity(intent);


                Toast.makeText(SearchShop.this, params, Toast.LENGTH_LONG).show();
                params.delete(0, params.length());
                al_cate1.clear();
                al_cate2.clear();
                al_cate3.clear();

            }
        });
    }
    //item1:五十音選択で選択された文字
    public void getCategory1(final String item1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CatagoryList,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray count = jsonObject.getJSONArray("category");
                            for (int i = 0; i < count.length(); i++) {
                                JSONObject data = count.getJSONObject(i);
                                //spinnerで何も選択されない時のために空白を挿入
                                if(i == 0) ad_cate1.add("");
                                //アダプターにセット
                                String str = data.getString("name");
                                //Toast.makeText(SearchShop.this,str,Toast.LENGTH_SHORT).show();
                                ad_cate1.add(data.getString("name"));
                            }
                            //エラーをToastで表示
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SearchShop.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("char", item1);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //item2:五十音選択で選択された文字
    public void getCategory2(final String item2) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CatagoryList,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray count = jsonObject.getJSONArray("category");
                            for (int i = 0; i < count.length(); i++) {
                                JSONObject data = count.getJSONObject(i);
                                //spinnerで何も選択されない時のために空白を挿入
                                if(i == 0) ad_cate2.add("");
                                //アダプターにセット
                                String str = data.getString("name");
                                //Toast.makeText(SearchShop.this,str,Toast.LENGTH_SHORT).show();
                                ad_cate2.add(data.getString("name"));
                            }
                            //エラーをToastで表示
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SearchShop.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("char", item2);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //item3:五十音選択で選択された文字
    public void getCategory3(final String item3) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CatagoryList,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray count = jsonObject.getJSONArray("category");
                            for (int i = 0; i < count.length(); i++) {
                                JSONObject data = count.getJSONObject(i);
                                //spinnerで何も選択されない時のために空白を挿入
                                if(i == 0) ad_cate3.add("");
                                //アダプターにセット
                                String str = data.getString("name");
                                //Toast.makeText(SearchShop.this,str,Toast.LENGTH_SHORT).show();
                                ad_cate3.add(data.getString("name"));
                            }
                            //エラーをToastで表示
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SearchShop.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("char", item3);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public ArrayList<String> getShopid(final String cate){
        final ArrayList<String> arrayList = new ArrayList<String>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_catagorylist,
                new Response.Listener<String>() {
                    @Override
                    //通信成功
                    public void onResponse(String response) {
                        try {
                            //Jsonデータを取得
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray count = jsonObject.getJSONArray("SHOPID");
                            for (int i = 0; i < count.length(); i++) {
                                JSONObject data = count.getJSONObject(i);
                                //アダプターにセット
                                String str = data.getString("shopid");
                                arrayList.add(str);
                                //Toast.makeText(SearchShop.this,arrayList.get(i),Toast.LENGTH_SHORT).show();
                            }
                            //エラーをToastで表示
                        } catch (JSONException e) {
                            //Toast.makeText(SearchShop.this, "bb",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                },//通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SearchShop.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            //サーバに送信する文字列を設定
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Mapにデータを格納
                params.put("str", cate);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return arrayList;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}