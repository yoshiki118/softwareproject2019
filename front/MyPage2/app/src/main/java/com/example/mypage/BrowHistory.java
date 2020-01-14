package com.example.mypage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BrowHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        yuser_info.xmlのファイルを呼び出す
        setContentView(R.layout.brow_history);

        // IDを元にImageViewオブジェクトを取得→→→→→→
        ImageView ivadd = (ImageView)this.findViewById(R.id.imageAdd);
        // ImageViewオブジェクトにクリックイベントを追加する
        ivadd.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // イメージ画像がクリックされたときに実行される処理
                        Toast.makeText(BrowHistory.this, "リストへ追加", Toast.LENGTH_LONG).show();
                    }
                }
        );//←←←←←←←←

        // IDを元にImageViewオブジェクトを取得→→→→→→
        ImageView ivdelete = (ImageView)this.findViewById(R.id.imageDelete);
        // ImageViewオブジェクトにクリックイベントを追加する
        ivdelete.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // イメージ画像がクリックされたときに実行される処理
                        Toast.makeText(BrowHistory.this, "削除", Toast.LENGTH_LONG).show();
                    }
                }
        );//←←←←←←←←
    }
}
