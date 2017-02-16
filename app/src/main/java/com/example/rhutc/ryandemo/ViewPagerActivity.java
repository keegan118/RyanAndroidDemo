package com.example.rhutc.ryandemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rhutc.ryandemo.adapter.ViewPagerAdapter;
import com.example.rhutc.ryandemo.bean.Book;
import com.example.rhutc.ryandemo.fragment.ContentFragment;
import com.example.rhutc.ryandemo.fragment.HistoryFragment;
import com.example.rhutc.ryandemo.fragment.LoginFragment;
import com.example.rhutc.ryandemo.util.UtilLog;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String s = i.getStringExtra("key");
        int number = b.getInt("Integer", 0);
        int fakeNumber = b.getInt("fake", 0);
        Book book = (Book)b.getSerializable("book");

        UtilLog.logD("ViewPagerActivity, value is: ", s);
        UtilLog.logD("ViewPagerActivity, number is: ", ""+number);
        UtilLog.logD("ViewPagerActivity, fakeNumber is: ", String.valueOf(fakeNumber));
        UtilLog.logD("ViewPagerActivity, Book Name:", book.getName());

        initializeViewPager();
    }

    private void initializeViewPager() {
        ViewPager vp = (ViewPager)findViewById(R.id.view_pager);
        ArrayList<Fragment> f = new ArrayList<>();
        f.add(new LoginFragment());
        f.add(new ContentFragment());
        f.add(new HistoryFragment());
        ViewPagerAdapter vpa = new ViewPagerAdapter(this.getSupportFragmentManager());
        vpa.setFragments(f);
        vp.setAdapter(vpa);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("message", "ViewPager");
        setResult(RESULT_OK, i);
        super.onBackPressed();
    }
}