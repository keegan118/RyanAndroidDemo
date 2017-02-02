package com.example.rhutc.ryandemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rhutc.ryandemo.adapter.ViewPagerAdapter;
import com.example.rhutc.ryandemo.fragment.ContentFragment;
import com.example.rhutc.ryandemo.fragment.HistoryFragment;
import com.example.rhutc.ryandemo.fragment.LoginFragment;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
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
}