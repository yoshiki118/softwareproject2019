package com.example.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.widget.Toast;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //my_list.xmlのファイルを呼び出す
        setContentView(R.layout.my_list);

        // IDを元にImageViewオブジェクトを取得→→→→→→
        ImageView ivadd = (ImageView)this.findViewById(R.id.imageAdd);
        // ImageViewオブジェクトにクリックイベントを追加する
        ivadd.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // イメージ画像がクリックされたときに実行される処理
                        Toast.makeText(MyList.this, "リスト作成", Toast.LENGTH_LONG).show();
                    }
                }
        );//←←←←←←←←

        // IDを元にImageViewオブジェクトを取得→→→→→→
        ImageView ivchange = (ImageView)this.findViewById(R.id.imageChange);
        // ImageViewオブジェクトにクリックイベントを追加する
        ivchange.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // イメージ画像がクリックされたときに実行される処理
                        Toast.makeText(MyList.this, "リスト名変更", Toast.LENGTH_LONG).show();
                    }
                }
        );//←←←←←←←←

        // IDを元にImageViewオブジェクトを取得→→→→→→
        ImageView ivsort = (ImageView)this.findViewById(R.id.imageSort);
        // ImageViewオブジェクトにクリックイベントを追加する
        ivsort.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // イメージ画像がクリックされたときに実行される処理
                        Toast.makeText(MyList.this, "順序変更", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MyList.this, "削除", Toast.LENGTH_LONG).show();
                    }
                }
        );//←←←←←←←←

    }
}

