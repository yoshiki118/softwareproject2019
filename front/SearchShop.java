package com.example.check;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class SearchShop extends AppCompatActivity {
    private ArrayAdapter<String> ad_spell1;
    private ArrayAdapter<String> ad_spell2;
    private ArrayAdapter<String> ad_spell3;

    private ArrayAdapter<String> ad_category1;
    private ArrayAdapter<String> ad_category2;
    private ArrayAdapter<String> ad_category3;

    private String spell1 = "";
    private String spell2 = "";
    private String spell3 = "";
    private String category1 = "";
    private String category2 = "";
    private String category3 = "";
    //ループ回数確認用
    private int num;


    private EditText searchText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //アクションバーに戻るボタンを実装
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        printSearchShop();

        initSpinners1();

        //ユーザトップアイコンの準備
        ImageButton homeIcon = (ImageButton)findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                   homePage();
            }
        });
        //検索アイコンの準備
        ImageButton searchIcon = (ImageButton)findViewById(R.id.searchIcon);
        searchIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                searchPage();
            }
        });
        //マイページアイコンの準備
        ImageButton myPageIcon = (ImageButton)findViewById(R.id.myPageIcon);
        myPageIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                myPage();
            }
        });



    }


    public void printSearchShop(){
        setContentView(R.layout.search_shop);
    }
    //ユーザトップ画面の処理の呼び出し
    public void homePage(){
        Intent home = new Intent(SearchShop.this, UserTop.class);
        startActivity(home);
    }
    //検索画面の処理の呼び出し
    public void searchPage(){
        Intent search = new Intent(SearchShop.this, SearchShop.class);
        startActivity(search);
    }
    //マイページ画面の処理の呼び出し
    public void myPage(){
        Intent myPage = new Intent(SearchShop.this, MyPage.class);
        startActivity(myPage);
    }

//spinnerの値をリアルタイムで取得
    private void initSpinners1(){
        //DBのURL
        //五十音のArrayList
        final ArrayList<String> al_spell1 = getCategory(URL);
        final ArrayList<String> al_spell2 = getCategory(URL);
        final ArrayList<String> al_spell3 = getCategory(URL);
        final ArrayList<String> al_category1 = getCategory(URL);
        final ArrayList<String> al_category2 = getCategory(URL);
        final ArrayList<String> al_category3 = getCategory(URL);


        //アダプタ作成
        ad_spell1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_spell2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_spell3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_category1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_category2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad_category3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        //spinner設定
        Spinner sp_spell1 = (Spinner)findViewById(R.id.spellspinner1);
        Spinner sp_spell2 = (Spinner)findViewById(R.id.spellspinner2);
        Spinner sp_spell3 = (Spinner)findViewById(R.id.spellpinner3);

        final Spinner sp_category1 = (Spinner)findViewById(R.id.category1);
        final Spinner sp_category2 = (Spinner)findViewById(R.id.category2);
        final Spinner sp_category3 = (Spinner)findViewById(R.id.category3);

        String[] labels = getResources().getStringArray(R.array.genre);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        String[] statelabels = getResources().getStringArray(R.array.area);
        ArrayAdapter<String> stateadapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statelabels);

        //アダプタ設定
        sp_spell1.setAdapter(ad_spell1);
        sp_spell2.setAdapter(ad_spell2);
        sp_spell3.setAdapter(ad_spell3);
        sp_category1.setAdapter(ad_category1);
        sp_category2.setAdapter(ad_category2);
        sp_category3.setAdapter(ad_category3);


        ad_spell1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_spell2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_spell3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_category1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_category2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ad_category3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Button searchButton  = findViewById(R.id.searchButton);

        //検索ボタン押下時の処理
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             String Add = category1 + category2 + category3;
            }
        });
        //五十音spinnerのリスナーを登録
        sp_spell1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item = spinner.getSelectedItem().toString();
                ad_category1.clear();
                category1 = "";
                if(position != 0){
                    //五十音コード

                    spell1 = "&id=" + al_spell1.get(position);
                    //spinnerで取得した都道府県に対応するエリアマスタ取得
                    al_category1 = getCategoryS(URL,item);
                    sp_category1.setAdapter(ad_category1);
                    ad_category1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ1spinnerのリスナー登録
        sp_category1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                //カテゴリ1の検索条件追加
                if(position != 0){
                    category1 = "&id=" + al_category1.get(position);
                }
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        //五十音spinner2のリスナーを登録
        sp_spell2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item = spinner.getSelectedItem().toString();
                ad_category2.clear();
                category2 = "";
                if(position != 0){
                    //五十音コード
                    spell2 = "&id=" + al_spell2.get(position);
                    //spinnerで取得した都道府県に対応するエリアマスタ取得
                    al_category2 = getCategoryS(URL,item);
                    sp_category2.setAdapter(ad_category2);
                    ad_category2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ2spinnerのリスナー登録
        sp_category2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                //カテゴリ2の検索条件追加
                if(position != 0){
                    category2 = "&id=" + al_category2.get(position);
                }
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        //五十音spinnerのリスナーを登録
        sp_spell1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                //選択した項目の取得
                String item = spinner.getSelectedItem().toString();
                ad_category3.clear();
                category3 = "";
                if(position != 0){
                    //五十音コード
                    spell1 = "&id=" + al_spell1.get(position);
                    //spinnerで取得した都道府県に対応するエリアマスタ取得
                    al_category3 = getCategoryS(URL,item);
                    sp_category3.setAdapter(ad_category3);
                    ad_category3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //カテゴリ1spinnerのリスナー登録
        sp_category1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //アイテムが選択されたとき
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                //カテゴリ1の検索条件追加
                if(position != 0){
                    category1 = "&id=" + al_category1.get(position);
                }
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

    }

//都道府県一覧のspinner呼び出し
private void setCityCategory(Spinner cityspinner1){
        //市区町村spinnerの有効化
    cityspinner1.setEnabled(true);
    String[] labels = getResources().getStringArray(R.array.city);
    ArrayAdapter<String> adapter
            = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
    cityspinner1.setAdapter(adapter);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
}
//市区町村spinnerの無効化
    private void NullCategory(Spinner cityspinner1){
        cityspinner1.setEnabled(false);

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

}
