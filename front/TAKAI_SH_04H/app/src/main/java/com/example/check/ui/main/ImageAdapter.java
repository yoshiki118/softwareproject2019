package com.example.check.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.check.R;
import android.graphics.Bitmap;
import java.util.List;


public class ImageAdapter extends BaseAdapter {


    private List<Bitmap> image;
    private LayoutInflater layoutInflater;
    private Context context;


    public ImageAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setImageList(List<Bitmap> imageList){
        this.image = imageList;
    }

    public int getCount(){
        return this.image.size();
    }

    public Object getItem(int position){ return this.image.get(position); }

    public long getItemId(int position){
        return 0;
    }

    static class ViewHolder { public ImageView imageView; }

    /*
    　リスト表示Viewにどのようなデータを紐づけるのかを設定
      positon:リスト番号、convertView:表示内容, parnet:リスト表示設定をする親(ListView):
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_image_list, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setImageBitmap(this.image.get(position));

        return convertView;
    }
}