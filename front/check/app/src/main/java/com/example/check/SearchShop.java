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

public class SearchShop extends AppCompatActivity {

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
        //画像の呼び出し
        ImageView imageView2 = findViewById(R.id.imageView);
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
        Spinner genrespinner1 = (Spinner)findViewById(R.id.genrespinner1);
        Spinner genrespinner2 = (Spinner)findViewById(R.id.genrespinner2);
        Spinner genrespinner3 = (Spinner)findViewById(R.id.genrespinner3);
        Spinner statespinner = (Spinner)findViewById(R.id.statespinner);
        String[] labels = getResources().getStringArray(R.array.genre);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        String[] statelabels = getResources().getStringArray(R.array.area);
        ArrayAdapter<String> stateadapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statelabels);
        statespinner.setAdapter(stateadapter);
        genrespinner1.setAdapter(adapter);
        genrespinner2.setAdapter(adapter);
        genrespinner3.setAdapter(adapter);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//カテゴリジャンル1の内容取得
        genrespinner1.setOnItemSelectedListener(new SpinnerSelectedListener1());
        //カテゴリジャンル2の内容取得
        genrespinner2.setOnItemSelectedListener(new SpinnerSelectedListener2());
        //カテゴリジャンル3の内容取得
        genrespinner3.setOnItemSelectedListener(new SpinnerSelectedListener3());
        //都道府県の内容取得
        statespinner.setOnItemSelectedListener(new checkAreaSpinnerSelecetedListener());
    }

    public class checkAreaSpinnerSelecetedListener implements  AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position, long id){
            Spinner spinner = (Spinner) parent;
            String str = spinner.getSelectedItem().toString();
            Spinner statespinner = (Spinner)findViewById(R.id.cityspinner);
            stateJudge(str,statespinner);
        }
        public  void  onNothingSelected(AdapterView parent){

        }

    }
    //カテゴリ1とカテゴリジャンル1の連携
    public class SpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position,long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
//カテゴリ1の内容取得
            Spinner categoryspinner1 = (Spinner)findViewById(R.id.category1);
            genreJudge(str,categoryspinner1);
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {
        }
    }
//カテゴリ2とカテゴリジャンル2の連携
    public class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position,long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
//カテゴリ2の内容取得
            Spinner categoryspinner2 = (Spinner)findViewById(R.id.category2);
            genreJudge(str,categoryspinner2);
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {
        }
    }
//カテゴリ3とカテゴリジャンル3の連携
    public class SpinnerSelectedListener3 implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent,View view, int position,long id) {
            // Spinner を取得
            Spinner spinner = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner.getSelectedItem().toString();
//カテゴリ3の選択内容を取得
            Spinner categoryspinner3 = (Spinner)findViewById(R.id.category3);
            genreJudge(str,categoryspinner3);
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {
        }
    }
    public void stateJudge(String str, Spinner statespinner){
        switch (str) {
            case "":
                NullCategory(statespinner);
                break;
            case "北海道":
                NullCategory(statespinner);
                break;
            case "東京":
                setCityCategory(statespinner);
                break;
            case "沖縄":
                NullCategory(statespinner);
                break;
        }
    }

    //カテゴリジャンルは何が選択されているかの取得
    public void genreJudge(String str, Spinner categoryspinner){
        switch (str) {
            case "":
                setNullCategory(categoryspinner);
                break;
            case "お店":
                setShopCategory(categoryspinner);
                break;
            case "料理":
                setCuisineCategory(categoryspinner);
                break;
            case "雰囲気":
                setMoodCategory(categoryspinner);
                break;
        }
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

    //ジャンルがお店のカテゴリ一覧のspinner呼び出し
    private void setShopCategory(Spinner categoryspinner1){
        String[] labels = getResources().getStringArray(R.array.shopcategory);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        categoryspinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
//ジャンルが料理のカテゴリ一覧のspinnerの呼び出し
    private void setCuisineCategory(Spinner categoryspinner1){
        String[] labels = getResources().getStringArray(R.array.cuisinecategory);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        categoryspinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    private void setMoodCategory(Spinner categoryspinner1){
        String[] labels = getResources().getStringArray(R.array.moodcategory);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        categoryspinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    private void setNullCategory(Spinner categoryspinner1){
        String[] labels = getResources().getStringArray(R.array.nullcategory);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        categoryspinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
