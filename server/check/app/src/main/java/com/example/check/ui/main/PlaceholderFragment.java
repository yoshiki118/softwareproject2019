package com.example.check.ui.main;

import android.app.Activity;
import android.content.Intent;
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
import com.example.check.CreateCategory;
import com.example.check.EditReview;
import com.example.check.MyApp;
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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.check.ShopInfo;
import com.example.check.ShopLogin;
import com.example.check.ShopSubmit;

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
    private Button button;
    private Spinner spinner;
    private ArrayAdapter<String> ad_mycate;
    private String item;
    //private Spinner spinner;
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
            root = inflater.inflate(R.layout.fragment_category, container, false);
        }


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {

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
                        new CoockHttpResponse(root, adapter, listView).execute(new URL("https://api.gnavi.co.jp/PhotoSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&shop_id=" + s));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else if (pageViewModel.getIndex() == 3) {

                    Parse(s, root);

                }else if (pageViewModel.getIndex() == 4){
                    button = root.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2 = new Intent(mactivity, EditReview.class);
                            intent2.putExtra("shopid", s);
                            startActivity(intent2);
                        }
                    });

                    final String url = "http://52.199.105.121/SelectReview.php";
                    getReviews(url, s, root);

                }else {
                    MyApp myApp = (MyApp)mactivity.getApplication();
                    String username = myApp.getTestString();

                    //Toast.makeText(mactivity, username, Toast.LENGTH_SHORT).show();
                    getCategories(s, root);
                    getmyCategory(username,root);
                    button = root.findViewById(R.id.btn_cate);
                    spinner = root.findViewById(R.id.sp_cate);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(item.equals("新規作成")) {
                                Intent intent = new Intent(mactivity, CreateCategory.class);
                                intent.putExtra("Id", s);
                                startActivity(intent);
                            }
                        }
                    });
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Spinner spinner = (Spinner)parent;
                            //選択した項目の取得
                            item = spinner.getSelectedItem().toString();
                            Toast.makeText(mactivity,item,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


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
                params.put("shopid", shopid);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);
    }


    public void getReviews(final String URL, final String post, final View root) {

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

                            if(jsonArray.length() == 0){
                                final TextView not_found = root.findViewById(R.id.notfound);
                                not_found.setText("まだレビューは投稿されていません");
                            }
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
                params.put("shopid", post);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);

    }



    public void getCategories(final String post, final View root) {

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
                            TextView not_found = root.findViewById(R.id.notfound);
                            not_found.setText("カテゴリーが見つけられませんでした");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopid", post);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);

    }
    public void getmyCategory(final String username, final View root) {
        final String URL = "http:/52.199.105.121/mycategory.php";

        final Spinner spinner = (Spinner) root.findViewById(R.id.sp_cate);

        final ArrayAdapter<String> ad_mycate = new ArrayAdapter<String>(mactivity,android.R.layout.simple_spinner_item);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ad_mycate.add("----------------------");
                            ad_mycate.add("新規作成");

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray count = jsonObject.getJSONArray("category");
                            for (int i = 0; i < count.length(); i++) {
                                JSONObject data = count.getJSONObject(i);
                                String name = data.getString("categoryname");
                                ad_mycate.add(name);
                               // Toast.makeText(mactivity,ad_mycate.getItem(i),Toast.LENGTH_SHORT).show();
                            }
                            spinner.setAdapter(ad_mycate);

                            ad_mycate.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mactivity);
        requestQueue.add(stringRequest);

    }



}