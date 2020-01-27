package com.example.check.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.check.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {



    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private static String mid;
    private static Activity mactivity;
//    private ArrayAdapter<String> arrayAdapter;
//    private ListView listt;



    public  static PlaceholderFragment newInstance(int index, String id, Activity activity) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        mid = id;
        mactivity = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        pageViewModel.setId(this.mid);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root;


        if(pageViewModel.getIndex()==1) {
            root = inflater.inflate(R.layout.fragment_top, container, false);

        }else if(pageViewModel.getIndex()==2){
            root = inflater.inflate(R.layout.fragment_image, container, false);

        }else if(pageViewModel.getIndex()==3){
            root = inflater.inflate(R.layout.fragment_info, container, false);

        }else if(pageViewModel.getIndex()==4){
            root = inflater.inflate(R.layout.fragment_review, container, false);
        }else {
            root = inflater.inflate(R.layout.fragment_review, container, false);
        }


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                if (pageViewModel.getIndex() == 1) {
                    try {
                        /* 非同期通信処理の呼び出し */
                        new ShopHttpResponse(root).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&id=" + s));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else if (pageViewModel.getIndex() == 2) {
                    ImageAdapter adapter = new ImageAdapter(mactivity);
                    final ListView listView = root.findViewById(R.id.list_label);
                    try {
                        new CoockHttpResponse(root, adapter, listView).execute(new URL("https://api.gnavi.co.jp/PhotoSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&shop_id=" +"g787501"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else if (pageViewModel.getIndex() == 3) {

                    Parse(s, root);

                }else if (pageViewModel.getIndex() == 4){

                    final String url = "http://52.199.105.121/SelectReview.php";
                    getReviews(url, "gc0a608", root);

                }else {
//                    listt = root.findViewById(R.id.listview);
//                    arrayAdapter = new ArrayAdapter<>(mactivity, R.layout.list);
                    getCategories("gc0a608", root);
                }
            }
        });

        return root;
    }

    private void Parse(final String shopid, View root) {

        final TextView textView = root.findViewById(R.id.text);
        final TextView textView1 = root.findViewById(R.id.textView1);
        final TextView textView2 = root.findViewById(R.id.textView2);
        final String URL = "http://52.199.105.121/SelectShopinfo.php";

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
//                                Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
//                                finish();
                            }else if(contents.equals("")){

//                                finish();
                            }

                            textView.setText(contents);
                            textView1.setText(date);
                            textView2.setText(time);

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            finish();
//                            Toast.makeText(Parse.this, "Error" + e.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        finish();
//                        Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopid", "gc0a608");
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);
    }


    public void getReviews(final String URL, final String post, View root) {

        //
        final ReviewAdapter adapter = new ReviewAdapter(mactivity);
        final ListView list = root.findViewById(R.id.list);



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            List<ReviewData> reviewList = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("review");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ReviewData reviews = new ReviewData();

                                JSONObject Username = jsonArray.getJSONObject(i);
                                String username = Username.getString("username");
                                JSONObject Cont = jsonArray.getJSONObject(i);
                                String cont = Cont.getString("reviewcontents");
                                JSONObject Date = jsonArray.getJSONObject(i);
                                String date = Date.getString("reviewdate");
                                JSONObject Time = jsonArray.getJSONObject(i);
                                String time = Time.getString("reviewtime");

                                reviews.setUsername(username);
                                reviews.setCont(cont);
                                reviews.setDate(date);
                                reviews.setTime(time);

                                reviewList.add(reviews);
                            }

                            adapter.setReviewList(reviewList);
                            list.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
//                            finish();
//                            Toast.makeText(Parse.this, "Error" + e.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        finish();
//                        Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopid", "gc0a608");
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);

    }

//    //サーバにアクセスしユーザが自店舗に付与したカテゴリを取得し,配列に格納
//    public void getCategories(final String shopid, final View view, final ArrayAdapter<String> arrayAdapter, final ListView listt) {
//
//
//
//        String URL_CatagoryList = "http:/52.199.105.121/U_category_list.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CatagoryList,
//                new Response.Listener<String>() {
//                    @Override
//                    //通信成功
//                    public void onResponse(String response) {
//                        try {
//
//                            //Jsonデータを取得
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray count = jsonObject.getJSONArray("category");
//                            arrayAdapter.clear();
//                            for (int i = 0; i < count.length(); i++) {
//                                JSONObject data = count.getJSONObject(i);
//                                //配列にカテゴリ名を格納
//                                arrayAdapter.add(data.getString("categoryname"));
//                                Toast.makeText(mactivity, "Error" + arrayAdapter.getItem(0), Toast.LENGTH_LONG).show();
//                            }
//                            listt.setAdapter(arrayAdapter);
//                            arrayAdapter.notifyDataSetChanged();
//                            //エラーをToastで表示
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },//通信失敗
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                }) {
//            @Override
//            //サーバに送信する文字列を設定
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //Mapにデータを格納
//                params.put("shopid", "gc0a608");
//                return params;
//
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
//        requestQueue.add(stringRequest);
//
//    }

    public void getCategories(final String post, View root) {

        //
        final String URL = "http:/52.199.105.121/U_category_list.php";
        final CategoryAdapter adapter = new CategoryAdapter(mactivity);
        final ListView list = root.findViewById(R.id.list);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            List<String> categorys = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("category");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject Category = jsonArray.getJSONObject(i);
                                String username = Category.getString("categoryname");
                                categorys.add(username);
                            }
                            adapter.setCategoryList(categorys);
                            list.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            finish();
//                            Toast.makeText(Parse.this, "Error" + e.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        finish();
//                        Toast.makeText(Parse.this, "お知らせはありません", Toast.LENGTH_LONG).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopid", "gc0a608");
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);

    }



}