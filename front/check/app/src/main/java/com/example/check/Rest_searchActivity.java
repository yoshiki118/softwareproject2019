package com.example.check;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.check.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Rest_searchActivity extends AppCompatActivity {
    //任意のパッケージ名に変更
    public static final String EXTRA_DATA = "com.example.fukuda.restsearch.DATA";
    private RequestQueue mQueue;
    //都道府県
    private ArrayAdapter<String> ad_pref;
    //詳細エリア
    private ArrayAdapter<String> ad_area_s;
    //大業態
    private ArrayAdapter<String> ad_daigyoutai;
    //小業態
    private ArrayAdapter<String> ad_shogyoutai;
    //
    private ArrayAdapter<String> ad_Ucategory;

    //都道府県マスタ取得API
    //private static String URL_Pref = "https://api.gnavi.co.jp/master/PrefSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&lang=ja";
    //エリアSマスタ取得API
    //private static String URL_AreaS = "https://api.gnavi.co.jp/master/GAreaSmallSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&lang=ja";
    //レストラン検索API
    //private static String URL_Rest_search = "https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9";
    //ユーザ作成カテゴリ取得API
    //private static String URL_CatagoryList = "http:/52.199.105.121/Ucategory.php";
    //大業態マスタ取得API
    //private static String URL_CategoryLarge = "https://api.gnavi.co.jp/master/CategoryLargeSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&lang=ja";
    //小業態マスタ取得API
    //private static String URL_CategorySmall = "https://api.gnavi.co.jp/master/CategorySmallSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&lang=ja";

    //都道府県マスタ取得API
    private static String URL_Pref = "https://api.gnavi.co.jp/master/PrefSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1&lang=ja";
    //エリアSマスタ取得API
    private static String URL_AreaS = "https://api.gnavi.co.jp/master/GAreaSmallSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1&lang=ja";
    //レストラン検索API
    private static String URL_Rest_search = "https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1";
    //ユーザ作成カテゴリ取得API
    private static String URL_CatagoryList = "http:/52.199.105.121/Ucategory.php";
    //大業態マスタ取得API
    private static String URL_CategoryLarge = "https://api.gnavi.co.jp/master/CategoryLargeSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1&lang=ja";
    //小業態マスタ取得API
    private static String URL_CategorySmall = "https://api.gnavi.co.jp/master/CategorySmallSearchAPI/v3/?keyid=2d6d76dbefd64c4b99ee433ca37f47a1&lang=ja";



    //パラメータ
    private String pref = "";
    private String areacode_s = "";
    private String category_l = "";
    private String category_s = "";

    //check
    private boolean Check;
    private String input_check;

    ArrayList<String> al_area_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //検索ボタン
        Button searchButton = findViewById(R.id.searchButton);
        Button btn_Ucate = findViewById(R.id.textView14);


        //都道府県のspinner
        Spinner sp_pref = (Spinner)findViewById(R.id.sp_pref);
        //詳細エリアのspinner
        final Spinner sp_area_s = (Spinner)findViewById(R.id.sp_area_s);
        //大業態のspinner
        Spinner sp_category_l = (Spinner) findViewById(R.id.sp_daigyotai);
        //小業態のspinner
        Spinner sp_category_s = (Spinner) findViewById(R.id.sp_shogyotai);

        //ユーザ作成カテゴリのButton
        //Button sp_Ucategory = findViewById(R.id.textView14);

        //ぐるなびapiから都道府県マスタ取得,格納
        final ArrayList<String> al_pref = getCategory(URL_Pref);

        //ぐるなびapiから大業態マスタ取得,格納
        final ArrayList<String> al_daigyotai = getCategory(URL_CategoryLarge);
        //ぐるなびapiから小業態マスタ取得,格納
        final ArrayList<String> al_shogyotai = getCategory(URL_CategorySmall);
        //ユーザ作成カテゴリ取得,格納
        //al_daigyotai = getCategory(URL_CatagoryList);

        ad_pref = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_area_s = new ArrayAdapter<String>(Rest_searchActivity.this, android.R.layout.simple_spinner_item);
        ad_daigyoutai = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_shogyoutai = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        //アダプタ設定
        sp_pref.setAdapter(ad_pref);

        sp_category_l.setAdapter(ad_daigyoutai);
        sp_category_s.setAdapter(ad_shogyoutai);

        //sp_Ucategory.setAdapter(ad_Ucategory);

        //リストに表示するためのレイアウトリソースを設定
        ad_pref.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_daigyoutai.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_shogyoutai.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        btn_Ucate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SearchShop.class);
                //検索結果画面に遷移
                startActivity(intent);

            }
        });
        //検索ボタン押下で検索結果画面に遷移、同時にURLの後ろにくっつけるパラメータ(検索条件)を渡す
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //キーワード入力欄の内容取得
                EditText search_text = findViewById(R.id.search_text);
                String name = "";
                //入力されているか
                if(!TextUtils.isEmpty(search_text.getText().toString())) name = "&name=" + search_text.getText().toString();
                //検索結果画面に渡すデータ
                String Add = pref + areacode_s + category_l + category_s + name;
                //Toast.makeText(Rest_searchActivity.this,Add,Toast.LENGTH_LONG).show();

                    //検索結果画面
                    Intent intent = new Intent(getApplication(), Search_result.class);
                    intent.putExtra("str", Add);
                    //検索結果画面に遷移
                    startActivity(intent);

            }
        });
        // 都道府県spinnerのリスナーを登録
        sp_pref.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item = spinner.getSelectedItem().toString();
                ad_area_s.clear();
                areacode_s = "";
                if(position != 0 ) {
                    //都道府県コード
                    pref = "&pref=" + al_pref.get(position);

                    //ぐるなびapiからspinnerで選択した都道府県に対応するエリアSマスタ取得,格納
                    al_area_s = getAreaS(URL_AreaS,item);
                    sp_area_s.setAdapter(ad_area_s);
                    ad_area_s.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }else{
                    pref = "";
                }
            }
            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        // 詳細エリアspinnerのリスナーを登録
        sp_area_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                //小業態の検索条件追加
                if(position != 0 ) {
                    //詳細エリアコード
                    areacode_s = "&areacode_s=" + al_area_s.get(position);
                }else{
                    areacode_s = "";
                }
            }
            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        // 大業態spinnerのリスナーを登録
        sp_category_l.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                //Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                //String item = (String) spinner.getItemAtPosition(position);

                //大業態の検索条件追加
                if(position != 0 ) {
                    //大業態コード
                    category_l = "&category_l=" + al_daigyotai.get(position) ;
                }else{
                    category_l = "";
                }

            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        // 小業態spinnerのリスナーを登録
        sp_category_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                //小業態の検索条件追加
                if(position != 0 ) {
                    //小業態コード
                    category_s = "&category_s=" + al_shogyotai.get(position) ;
                }else{
                    category_s = "";
                }
            }
            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public ArrayList<String> getCategory(final String URL) {
        mQueue = Volley.newRequestQueue(this);
        final ArrayList<String> arrayList = new ArrayList<String>();
        mQueue.add(new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //都道府県マスタ取得APIの場合
                            if(URL.equals(URL_Pref)) {
                                // JSONのパース
                                JSONArray jsonArray = response.getJSONArray("pref");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject Pref_code = jsonArray.getJSONObject(i);
                                    String pref_code = Pref_code.getString("pref_code");
                                    JSONObject Pref_name = jsonArray.getJSONObject(i);
                                    String pref_name = Pref_name.getString("pref_name");
                                    //spinnerで何も選択されない時のために空白を挿入
                                    if(i == 0){
                                        ad_pref.add("");
                                        arrayList.add("");
                                    }
                                    ad_pref.add(pref_name);
                                    arrayList.add(pref_code);
                                }
                            }
                            //大業態マスタ取得APIの場合
                            if(URL.equals(URL_CategoryLarge)) {
                                // JSONのパース
                                JSONArray jsonArray = response.getJSONArray("category_l");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject cate_l_name = jsonArray.getJSONObject(i);
                                    String category_l_name = cate_l_name.getString("category_l_name");
                                    JSONObject cate_l_code = jsonArray.getJSONObject(i);
                                    String category_l_code = cate_l_code.getString("category_l_code");
                                    //spinnerで何も選択されない時のために空白を挿入
                                    if(i == 0){
                                        ad_daigyoutai.add("");
                                        arrayList.add("");

                                    }
                                    ad_daigyoutai.add(category_l_name);
                                    arrayList.add(category_l_code);
                                }
                            }
                            //小業態マスタ取得APIの場合
                            if(URL.equals(URL_CategorySmall)) {
                                // JSONのパース
                                JSONArray jsonArray = response.getJSONArray("category_s");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject cate_s = jsonArray.getJSONObject(i);
                                    String category_s_name = cate_s.getString("category_s_name");
                                    JSONObject cate_s_code = jsonArray.getJSONObject(i);
                                    String category_s_code = cate_s_code.getString("category_s_code");
                                    //spinnerで何も選択されない時のために空白を挿入
                                    if(i == 0){
                                        ad_shogyoutai.add("");
                                        arrayList.add("");
                                    }
                                    ad_shogyoutai.add(category_s_name);
                                    arrayList.add(category_s_code);
                                }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        // エラー表示
                    }
                }));
        return arrayList;
    }
    public ArrayList<String> getAreaS(final String URL,final String category_L_name) {
        mQueue = Volley.newRequestQueue(this);
        final ArrayList<String> arrayList = new ArrayList<String>();
        mQueue.add(new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //エリアSマスタ取得APIの場合
                            if(URL.equals(URL_AreaS)) {
                                // JSONのパース
                                JSONArray jsonArray = response.getJSONArray("garea_small");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject Areacode_s = jsonArray.getJSONObject(i);
                                    String areacode_s = Areacode_s.getString("areacode_s");
                                    JSONObject Areaname_s = jsonArray.getJSONObject(i);
                                    String areaname_s = Areaname_s.getString("areaname_s");
                                    //引数の都道府県と比較する文字列
                                    String comp_pref = jsonArray.getJSONObject(i).getJSONObject("pref").getString("pref_name");

                                    //spinnerで何も選択されない時のために空白を挿入
                                    if(i == 0){
                                        ad_area_s.add("");
                                        arrayList.add("");
                                    }
                                    //引数の都道府県に一致する場合は詳細エリアのアダプタに格納
                                    if(category_L_name.equals(comp_pref)) {
                                        ad_area_s.add(areaname_s);
                                        arrayList.add(areacode_s);
                                    }
                                }

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        // エラー表示
                    }
                }));
        return arrayList;
    }
}

