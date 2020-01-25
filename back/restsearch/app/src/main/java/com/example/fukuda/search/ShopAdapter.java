package com.example.fukuda.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.fukuda.restsearch.R;

import java.util.List;


public class ShopAdapter extends BaseAdapter {


    private List<ShopList> shopsList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ShopAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setShopList(List<ShopList> shopsList){
        this.shopsList = shopsList;
    }

    public int getCount(){ return this.shopsList.size(); }

    public Object getItem(int position){ return shopsList.get(position); }

    public long getItemId(int position){
        return 0;
    }

    static class ViewHolder {
        public TextView name;
        public TextView opentime;
        public TextView pr;
        public ImageView image;
    }

    /*
    　リスト表示Viewにどのようなデータを紐づけるのかを設定
      positon:リスト番号、convertView:表示内容, parnet:リスト表示設定をする親(ListView):
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shop_list, parent, false);
            holder = new ViewHolder();
            holder.name =  convertView.findViewById(R.id.name);
            holder.opentime = convertView.findViewById(R.id.opentime);
            holder.pr = convertView.findViewById(R.id.pr);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShopList shopsItem = this.shopsList.get(position);

        holder.name.setText(shopsItem.getName());
        holder.opentime.setText(shopsItem.getOpentime());
        holder.pr.setText(shopsItem.getPrshort());

        holder.image.setImageBitmap(shopsItem.getImage());

        return convertView;

    }
}