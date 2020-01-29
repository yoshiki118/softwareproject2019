package com.example.fukuda.shopinfos.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;

import com.example.fukuda.shopinfos.R;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private static String mid;
    private static Activity mactivity;

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
        //pageViewModelへどのタブ（番号漬け）を表示するのか値を渡す
        pageViewModel.setIndex(index);
        pageViewModel.setId(this.mid);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root;
        final TextView textView;
        if(pageViewModel.getIndex()==1) {
            root = inflater.inflate(R.layout.fragment_main3, container, false);
             textView = root.findViewById(R.id.section_label);
        }else if(pageViewModel.getIndex()==2){
             root = inflater.inflate(R.layout.fragment_main2, container, false);
             textView = root.findViewById(R.id.section_label);
        }else {
             root = inflater.inflate(R.layout.fragment_main, container, false);
             textView = root.findViewById(R.id.section_label);
        }

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(s != "a") {
                    try {
                        /* 非同期通信処理の呼び出し */
                        new ShopHttpResponse(root).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&id=kdax400"));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else {
                     //textView.setText(s);
                }

            }
        });

        return root;
    }


}