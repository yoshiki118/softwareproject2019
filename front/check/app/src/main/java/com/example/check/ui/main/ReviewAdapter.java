package com.example.check.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.check.R;

import java.util.List;

public class ReviewAdapter extends BaseAdapter {

    private List<ReviewData> reviewList;
    private LayoutInflater layoutInflater;
    private Context context;


    public ReviewAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setReviewList(List<ReviewData> reviewList){ this.reviewList = reviewList; }

    public int getCount(){
        return this.reviewList.size();
    }

    public Object getItem(int position){ return reviewList.get(position); }

    public long getItemId(int position){
        return 0;
    }

    static class ViewHolder {
        public TextView username;
        public TextView contents;
        public TextView date;
        public TextView time;
    }

    /*
    　リスト表示Viewにどのようなデータを紐づけるのかを設定
      positon:リスト番号、convertView:表示内容, parnet:リスト表示設定をする親(ListView):
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_review_list, parent, false);
            holder = new ViewHolder();
            holder.username = convertView.findViewById(R.id.username);
            holder.contents = convertView.findViewById(R.id.cont);
            holder.date =  convertView.findViewById(R.id.date);
            holder.time = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ReviewData reviews = this.reviewList.get(position);

        holder.username.setText(reviews.getUsername());
        holder.contents.setText(reviews.getCont());
        holder.date.setText(reviews.getDate());
        holder.time.setText(reviews.getTime());

        return convertView;
    }
}
