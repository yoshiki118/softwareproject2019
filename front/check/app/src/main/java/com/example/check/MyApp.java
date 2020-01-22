package com.example.check;

import android.app.Application;

public class MyApp extends Application {

    private String testString="abc";
    @Override
    public void onCreate(){
        super.onCreate();
    }

    public String getTestString(){
        return testString;
    }

    public void setTestString(String str){
        testString = str;
    }
}
