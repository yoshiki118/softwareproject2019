package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.Window;
import android.content.Intent;
public class MainActivity extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenMain();
    }
    public void setScreenMain() {
        /*ユーザ登録モジュールの呼び出し*/
        Intent intent = new Intent(MainActivity.this, UserHeader.class);

        startActivity(intent);
        Intent usersubmit = new Intent(MainActivity.this, UserSubmit.class);
        startActivity(usersubmit);
/*ヘッダーの呼び出し*/




    }
        /*spinnerのドロップダウンを出す処理*/

        /*Spinner spinner = findViewById(R.id.Spinner);*/


    /*public void sendMessage(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
    }*/
}
