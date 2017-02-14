package com.example.rhutc.ryandemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void toActivity(Class name){
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    public void toastShort(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
