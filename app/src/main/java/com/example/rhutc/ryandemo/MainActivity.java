package com.example.rhutc.ryandemo;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rhutc.ryandemo.bean.Book;
import com.example.rhutc.ryandemo.util.UtilLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnTouchListener {

    private ImageButton bt1, bt3;
    private ImageButton bt2;
    private ImageButton topLeftButton;
    private ImageButton topRightButton;

    private GestureDetector mGestureDetector;

    @BindView(R.id.main_fl)
    FrameLayout fl;

    @OnClick(R.id.animator_button)
    public void animatorClick(){
        Intent i = new Intent(this, AnimatorActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.bt2)
    public void button2Click(){
        // This way doesn't get a response from the DialogActivity
//        toActivity(DialogActivity.class);

        // This way gets a response from the DialogActivity
        Intent i = new Intent(this, DialogActivity.class);
        startActivityForResult(i, 2);
    }

    @OnClick(R.id.timer_button)
    public void timerButtonClick(){
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.animation_button)
    public void animationButtonClick(){
        Intent i = new Intent(this, AnimationActivity.class);
        startActivity(i);
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
        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
        fl.setOnTouchListener(this);
    }

    private void initialView(){
        bt1 = (ImageButton)findViewById(R.id.bt1);
//        bt2 = (ImageButton)findViewById(R.id.bt2);
        bt3 = (ImageButton)findViewById(R.id.bt3);
        topLeftButton = (ImageButton)findViewById(R.id.topLeftButton);
        topRightButton = (ImageButton)findViewById(R.id.topRightButton);
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

        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LaunchModeActivityA.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
        Toast.makeText(this,"Button 2 was Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            toastShort("onDown");
            return true;
        }
        @Override
        public void onShowPress(MotionEvent e) {
            toastShort("onShowPress");
        }
        @Override
        public void onLongPress(MotionEvent e) {
            toastShort("onLongPress");
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            toastShort("onSingleTapUp");
            return true;
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            toastShort("onSingleTapConfirmed");
            return true;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            toastShort("onScroll");
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            toastShort("onFling");
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            toastShort("onDoubleTap");
            return true;
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            toastShort("onDoubleTapEvent");
            return true;
        }
    }
}