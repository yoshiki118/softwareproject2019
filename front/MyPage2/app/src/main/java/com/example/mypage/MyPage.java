package com.example.mypage;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        //***************マイリストへの遷移**********
        //Button型の「buttoncategory」はID『buttoncategory』ですよ
        Button buttoncategory = (Button) findViewById(R.id.buttonmylist);
        //「buttoncategory」が押された時の処理は以下の通りです→→→→→→
        buttoncategory.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(ShopHome.this, ShopSample.class);
                ～.thisの～の部分が現在のJavaファイル名
                ＿.classの＿の部分が遷移したいファイル名
                →ここで画面遷移をするっていうとる*/
                Intent intent = new Intent(MyPage.this, MyList.class);
                //飛ばすで～
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************情報編集への遷移**************
        Button buttonyuserinfo = (Button) findViewById(R.id.yuserinfo);
        //「yuserinfo」が押された時の処理は以下の通りです→→→→→→
        buttonyuserinfo.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, YuserInfo.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************閲覧履歴への遷移**************
        Button buttonbrow = (Button) findViewById(R.id.browhistory);
        //「browhistory」が押された時の処理は以下の通りです→→→→→→
        buttonbrow.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, BrowHistory.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************レビュー履歴への遷移**************
        Button buttonreview = (Button) findViewById(R.id.reviewhistory);
        //「reviewhistory」が押された時の処理は以下の通りです→→→→→→
        buttonreview.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, ReviewHistory.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************クーポン一覧への遷移**************
        Button buttoncoupon = (Button) findViewById(R.id.couponlist);
        //「couponlist」が押された時の処理は以下の通りです→→→→→→
        buttoncoupon.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, CouponList.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************ユーザの問い合わせへの遷移**************
        Button buttoninquiry = (Button) findViewById(R.id.yuserinquiry);
        //「yuserinquiry」が押された時の処理は以下の通りです→→→→→→
        buttoninquiry.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, YuserInquiry.class);
                //YuserInquiry.classが仮で作った問い合わせ用クラスなので置き換えてください
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************有料会員登録への遷移**************
        Button buttonpaid = (Button) findViewById(R.id.paidmember);
        //「paidmember」が押された時の処理は以下の通りです→→→→→→
        buttonpaid.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, PaidChange.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

        //************退会画面への遷移**************
        Button withdraw = (Button) findViewById(R.id.buttonwithdraw);
        //「buttonwithdraw」が押された時の処理は以下の通りです→→→→→→
        withdraw.setOnClickListener(new View.OnClickListener() {
            //押された時の処理は以下の通りです
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, Withdraw.class);
                startActivity(intent);
            }
        });
        //←←←←←←ここまで

    }
}
