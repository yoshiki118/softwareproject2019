package com.example.check;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.core.view.MenuItemCompat;

public class SearchActivity extends SearchShop{
    private SearchView mSearchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.activity_memory, menu);
        MenuItem menuItem = menu.findItem(R.id.toolbar_menu_search);

        mSearchView = (SearchView) menuItem.getActionView();

        mSearchView.setIconifiedByDefault(true);

        mSearchView.setSubmitButtonEnabled(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String query){
                return false;
            }
            public boolean onQueryTextChange(String newText){
                //文字列検索用の独自メソッド
                //suggestion_search(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

}
