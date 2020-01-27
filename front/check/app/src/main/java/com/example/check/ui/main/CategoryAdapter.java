package com.example.check.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.check.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<String> categoryList;
    private LayoutInflater layoutInflater;
    private Context context;


    public CategoryAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setCategoryList(List<String> categoryList){ this.categoryList = categoryList; }

    public int getCount(){ return this.categoryList.size(); }

    public Object getItem(int position){ return categoryList.get(position); }

    public long getItemId(int position){
        return 0;
    }

    static class ViewHolder {
        public TextView categoryname;
    }

    /*
    　リスト表示Viewにどのようなデータを紐づけるのかを設定
      positon:リスト番号、convertView:表示内容, parnet:リスト表示設定をする親(ListView):
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_category_list, parent, false);
            holder = new ViewHolder();
            holder.categoryname = convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        String categorys = this.categoryList.get(position);

        holder.categoryname.setText(categorys);

        return convertView;
    }
}
