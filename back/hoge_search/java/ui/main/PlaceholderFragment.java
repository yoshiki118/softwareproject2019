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

import com.example.check.R;

import java.net.MalformedURLException;
import java.net.URL;
import android.widget.ListView;

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

        }else {
            root = inflater.inflate(R.layout.fragment_review, container, false);
        }


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                if(pageViewModel.getIndex()==1) {
                    try {
                        /* 非同期通信処理の呼び出し */
                        new ShopHttpResponse(root).execute(new URL("https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&id="+s));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else if(pageViewModel.getIndex()==2){
                    ImageAdapter adapter = new ImageAdapter(mactivity);
                    final ListView listView = root.findViewById(R.id.list_label);
                    try {
                        new CoockHttpResponse(root, adapter, listView).execute(new URL("https://api.gnavi.co.jp/PhotoSearchAPI/v3/?keyid=85d315b3b18c6c8a69c7f0bb5f8023f9&shop_id="+s));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }
        });

        return root;
    }


}