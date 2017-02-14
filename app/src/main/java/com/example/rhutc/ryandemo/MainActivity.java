package com.example.rhutc.ryandemo;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private ImageButton bt1, bt3;
//    private ImageButton bt2;
    private ImageButton topLeftButton;

    @OnClick(R.id.bt2)
    public void button2Click(){
        toActivity(DialogActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        ButterKnife.bind(this);
    }

    private void initialView(){
        bt1 = (ImageButton)findViewById(R.id.bt1);
//        bt2 = (ImageButton)findViewById(R.id.bt2);
        bt3 = (ImageButton)findViewById(R.id.bt3);
        topLeftButton = (ImageButton)findViewById(R.id.topLeftButton);
    }

    private void initalListener(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Button 1", Toast.LENGTH_SHORT).show();
            }
        });

//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Button 2", Toast.LENGTH_SHORT).show();
//            }
//        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Using the methods that we made in BaseActivity because this class extends it
                toastShort("Button 3");
                toActivity(ListViewActivity.class);

                // Old way to do it
                //Intent intent = new Intent(v.getContext(), ListViewActivity.class);
                //startActivity(intent);
            }
        });
        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "View Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
        Toast.makeText(this,"Button 2 was Clicked", Toast.LENGTH_SHORT).show();
    }
}