package com.example.fukuda.testlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.lang.Object;

public class MyCategoryview extends AppCompatActivity {
    private static String URL_CatagoryList = "http:/52.199.105.121/u_category_list.php";
    private static String URL_uCategory_Delete = "http:/52.199.105.121/u_category_delete.php";
    //店舗id
    private static String shopid = "gc0a608";



    //削除対象
    private String SELECTED_ITEM = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // itemを表示するTextViewが設定されているlist.xmlを指す
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list);
        // activity_main.xmlのlistViewにListViewをセット
        final ListView listView = findViewById(R.id.listview);


        //データベースから取得後list表示
        update_list(shopid,listView,arrayAdapter);

        // リスト項目をクリックした時の処理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            /**
             parent ListView
             view 選択した項目
             position 選択した項目の添え字
             id 選択した項目のID
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 選択した項目をTextViewにキャストした後、Stringにキャスト
                String selectedItem = (String)((TextView) view).getText();
                SELECTED_ITEM = selectedItem;
                // ダイアログ表示
                alertCheck(selectedItem,arrayAdapter);
            }
        });

    }

        //サーバにアクセスしユーザが自店舗に付与したカテゴリを取得し,配列に格納
        public void getCategories(final String shopid,final ListView listView,final ArrayAdapter arrayAdapter) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CatagoryList,
                    new Response.Listener<String>() {
                        @Override
                        //通信成功
                        public void onResponse(String response) {
                            try {
                                //Jsonデータを取得
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray count = jsonObject.getJSONArray("category");
                                arrayAdapter.clear();
                                for (int i = 0; i < count.length(); i++) {
                                    JSONObject data = count.getJSONObject(i);
                                    //配列にカテゴリ名を格納
                                    arrayAdapter.add(data.getString("categoryname"));
                                }
                                listView.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
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
                            Toast.makeText(MyCategoryview.this, "通信に失敗しました。" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                //サーバに送信する文字列を設定
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Mapにデータを格納
                    params.put("shopid", shopid);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    private void alertCheck(String item, final ArrayAdapter arrayAdapter) {
        //idx=0 : 削除, idx=1 : cancel
        String[] alert_menu = {"削除", "cancel"};

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(item);
        alert.setItems(alert_menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int idx) {
                // リストアイテムを選択したときの処理
                // アイテムの削除
                if (idx == 0) {
                    deleteCheck(arrayAdapter);
                }
                // cancel
                else {
                    Log.d("debug", "cancel");
                }
            }
        });
        alert.show();
    }

    private void deleteCheck(final ArrayAdapter arrayAdapter) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // AlertDialogのタイトル設定

        alertDialogBuilder.setTitle("削除");

        // AlertDialogのメッセージ設定
        alertDialogBuilder.setMessage("本当に削除しますか？");

        // AlertDialogのYesボタンのコールバックリスナーを登録
        alertDialogBuilder.setPositiveButton("はい",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //選択したカテゴリを削除
                        //データベースから削除
                        deleteItem(SELECTED_ITEM);
                        //listviewから削除
                        arrayAdapter.remove(SELECTED_ITEM);
                        //更新
                        arrayAdapter.notifyDataSetChanged();

                    }
                });
        // AlertDialogのNoボタンのコールバックリスナーを登録
        alertDialogBuilder.setNeutralButton("いいえ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // AlertDialogのキャンセルができるように設定
        alertDialogBuilder.setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        // AlertDialogの表示
        alertDialog.show();
    }
    private void update_list(final String shopid,final ListView listView,final ArrayAdapter arrayAdapter){
        arrayAdapter.clear();
        getCategories(shopid,listView,arrayAdapter);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    //選択したカテゴリ名を削除するためにサーバに接続
    private void deleteItem(final String selectedItem) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_uCategory_Delete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(MyCategoryview.this,
                                        SELECTED_ITEM+"を削除しました", Toast.LENGTH_SHORT)
                                        .show();
                            }


                            //エラー処理
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MyCategoryview.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }


            },
                new Response.ErrorListener() { //エラー内容をToastで表示
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MyCategoryview.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //選択したカテゴリ名を送信
                    params.put("selectedItem", selectedItem);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }

}