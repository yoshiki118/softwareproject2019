package com.example.check;

import android.app.Application;
//＠＠＠＠＠＠＠データの受け渡しをするよ＠＠＠＠＠＠
//グローバル関数やから、どこからでも呼び出し可能だよ
public class MyApp extends Application {

    //IDを別ページでも呼び出せるようの変数
    private String StringIDString="abc";
    @Override
    public void onCreate(){
        super.onCreate();
    }
    //strをStringIDStringに置いておくよ
    public void setTestString(String str){
        StringIDString = str;
    }

    //StringIDStringの中身を呼び出し元に返すよ
    public String getTestString(){
        return StringIDString;
    }

}
