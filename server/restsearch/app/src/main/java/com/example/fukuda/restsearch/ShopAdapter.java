package com.example.fukuda.restsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

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

    /* 　getCount, getItem, getItemId getView は　BaseAdapterを実装の際必須 */
    public int getCount(){
        return this.shopsList.size();
    }

    public Object getItem(int position){ return shopsList.get(position); }

    public long getItemId(int position){
        return 0;
    }

    /*
    　リスト表示Viewにどのようなデータを紐づけるのかを設定
      positon:リスト番号、convertView:表示内容, parnet:リスト表示設定をする親(ListView):
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        convertView = layoutInflater.inflate(R.layout.shop_list, parent, false);

        ShopList shopsItem = this.shopsList.get(position);

        //　店舗名
        TextView nameTextView = convertView.findViewById(R.id.name);
        nameTextView.setText(shopsItem.getName());
        //　営業時間
        TextView addressTextView = convertView.findViewById(R.id.opentime);
        addressTextView.setText(shopsItem.getOpentime());
        //　PR文
        TextView prTextView = convertView.findViewById(R.id.pr);
        prTextView.setText(shopsItem.getPrshort());
        //　画像
        ImageView imageView = convertView.findViewById(R.id.image);
        imageView.setImageBitmap(shopsItem.getImage());

        return convertView;

    }
}