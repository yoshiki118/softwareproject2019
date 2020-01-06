package com.example.test;

import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

    public class UserHeader extends Activity {
        /** Called when the activity is first created. */
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タイトルバーのカスタマイズを設定可能にする

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
            setContentView(R.layout.user_submit);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        //Activity終了時に処理をもとのActivityに戻す
        finish();


    }
}
