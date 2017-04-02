package com.example.rhutc.ryandemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhutc.ryandemo.adapter.ListViewAdapter;
import com.example.rhutc.ryandemo.adapter.NavDrawerAdapter;
import com.example.rhutc.ryandemo.adapter.ViewPagerAdapter;
import com.example.rhutc.ryandemo.bean.Book;
import com.example.rhutc.ryandemo.dialog.CustomDialog;
import com.example.rhutc.ryandemo.dialog.CustomDialog2;
import com.example.rhutc.ryandemo.fragment.Fragment1;
import com.example.rhutc.ryandemo.fragment.Fragment2;
import com.example.rhutc.ryandemo.fragment.Fragment3;
import com.example.rhutc.ryandemo.fragment.Fragment4;
import com.example.rhutc.ryandemo.fragment.Fragment5;
import com.example.rhutc.ryandemo.fragment.Fragment6;
import com.example.rhutc.ryandemo.fragment.Fragment7;
import com.example.rhutc.ryandemo.fragment.Fragment8;
import com.example.rhutc.ryandemo.util.UtilLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.security.AccessController.getContext;

public class MainActivity extends BaseActivity implements View.OnTouchListener {

    private ImageButton bt1, bt3;
    private ImageButton bt2;
    private ImageButton topLeftButton;
    private ImageButton topRightButton;
    private ListView navDrawer;

    private GestureDetector mGestureDetector;
    private DrawerLayout mDrawerLayout;

    @BindView(R.id.main_fl)
    FrameLayout fl;

    @OnClick(R.id.quiz4)
    public void quiz4Click() {
        CustomDialog2 dialog = new CustomDialog2(this, new CustomDialog2.ICustomDialogEventListener2() {
            @Override
            public void onClickCancel() {
                toastShort("Cancel was selected");
                // Do the same thing as button 1
                bt1.callOnClick();
            }

            @Override
            public void onClickOk(int radioID) {
                toastShort("Ok was selected");
                if (radioID == R.id.dialog_activity_rb) {
                    // Do the same thing as button 2
                    button2Click();
                } else if (radioID == R.id.list_view_activity_rb) {
                    // Do the same thing as button 3
                    bt3.callOnClick();
                }
            }
        });
        dialog.show();
    }

    @OnClick(R.id.animator_button)
    public void animatorClick() {
        Intent i = new Intent(this, AnimatorActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.bt2)
    public void button2Click() {
        // This way doesn't get a response from the DialogActivity
//        toActivity(DialogActivity.class);

        // This way gets a response from the DialogActivity
        Intent i = new Intent(this, DialogActivity.class);
        startActivityForResult(i, 2);
    }

    @OnClick(R.id.timer_button)
    public void timerButtonClick() {
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.animation_button)
    public void animationButtonClick() {
        Intent i = new Intent(this, AnimationActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.topLeftButton)
    public void toggleDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
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
        initalNavDrawer();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ButterKnife.bind(this);
        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
        fl.setOnTouchListener(this);
    }

    private void initalNavDrawer() {
        navDrawer = (ListView) findViewById(R.id.left_drawer);
        ArrayList<String> sample = new ArrayList<>();
        sample.add("Main");
        sample.add("ListView");
        sample.add("ViewPager");
        sample.add("Activity A");
        sample.add("Activity B");
        sample.add("Activity C");
        sample.add("Activity D");
        NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(this, sample);
        navDrawer.setAdapter(navDrawerAdapter);
        navDrawer.setOnItemClickListener(new DrawerItemClickListener());
    }

    private void initialView() {
        bt1 = (ImageButton) findViewById(R.id.bt1);
//        bt2 = (ImageButton)findViewById(R.id.bt2);
        bt3 = (ImageButton) findViewById(R.id.bt3);
        topLeftButton = (ImageButton) findViewById(R.id.topLeftButton);
        topRightButton = (ImageButton) findViewById(R.id.topRightButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
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

    private void initalListener() {
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

    public void onClick(View v) {
        Toast.makeText(this, "Button 2 was Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
//            toastShort("onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            toastShort("onShowPress");
        }

        @Override
        public void onLongPress(MotionEvent e) {
//            toastShort("onLongPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
//            toastShort("onSingleTapUp");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
//            toastShort("onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            toastShort("onScroll");
            return true;
            //
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            toastShort("onFling");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
//            toastShort("onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
//            toastShort("onDoubleTapEvent");
            return true;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i;
            switch (position) {
                case 0:
                    //do nothing
                    break;
                case 1:
                    //go to list view
                    i = new Intent(parent.getContext(), ListViewActivity.class);
                    startActivity(i);
                    break;
                case 2:
                    //go to view pager and add all the necessary info
                    //so there are no null pointers
                    // Using an intent to pass information
                    i = new Intent(parent.getContext(), ViewPagerActivity.class);
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
                    break;
                case 3:
                    //go to activity a
                    i = new Intent(parent.getContext(), LaunchModeActivityA.class);
                    startActivity(i);
                    break;
                case 4:
                    //go to activity b
                    i = new Intent(parent.getContext(), LaunchModeActivityB.class);
                    startActivity(i);
                    break;
                case 5:
                    //go to activity c
                    i = new Intent(parent.getContext(), LaunchModeActivityC.class);
                    startActivity(i);
                    break;
                case 6:
                    //go to activity d
                    i = new Intent(parent.getContext(), LaunchModeActivityD.class);
                    startActivity(i);
                    break;
                default:
                    //do nothing
                    break;
            }
        }
    }
}