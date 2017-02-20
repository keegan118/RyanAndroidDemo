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

import com.example.rhutc.ryandemo.bean.Book;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private ImageButton bt1, bt3;
    private ImageButton bt2;
    private ImageButton topLeftButton;

    @OnClick(R.id.bt2)
    public void button2Click(){
        // This way doesn't get a response from the DialogActivity
//        toActivity(DialogActivity.class);

        // This way gets a response from the DialogActivity
        Intent i = new Intent(this, DialogActivity.class);
        startActivityForResult(i, 2);
    }

    // Without Butterknife we must do this to set a listener
//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Button 2", Toast.LENGTH_SHORT).show();
//            }
//        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initalListener();
        ButterKnife.bind(this);
    }

    private void initialView(){
        bt1 = (ImageButton)findViewById(R.id.bt1);
//        bt2 = (ImageButton)findViewById(R.id.bt2);
        bt3 = (ImageButton)findViewById(R.id.bt3);
        topLeftButton = (ImageButton)findViewById(R.id.topLeftButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                String message = data.getStringExtra("message");
                toastShort("From ViewPager: " + message);
                break;
            case 2:
                toastShort("Dialog");
                break;
            case 3:
                toastShort("ListView");
                break;
        }
    }

    private void initalListener(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Button 1", Toast.LENGTH_SHORT).show();

                // Using an intent to pass information
                Intent i = new Intent(v.getContext(), ViewPagerActivity.class);
                i.putExtra("key", "value");
                Bundle b = new Bundle();
                b.putInt("Ingeter", 12345);

                //Making a book object
                Book book = new Book();
                book.setName("Book's Name");
                book.setAuthor("Book's Author");
                b.putSerializable("book", book);

                i.putExtras(b);
                startActivityForResult(i, 1);

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Using the methods that we made in BaseActivity because this class extends it
                toastShort("Button 3");

                // Broken
                Intent i = new Intent(v.getContext(), ListViewActivity.class);
                startActivityForResult(i, 3);

//                toActivity(ListViewActivity.class);
            }
        });

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "View Page", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
//                startActivity(intent);
//                Intent i = new Intent(v.getContext(), ViewPagerActivity.class);
//                startActivityForResult(i, 3);
            }
        });
    }

    public void onClick(View v){
        Toast.makeText(this,"Button 2 was Clicked", Toast.LENGTH_SHORT).show();
    }
}