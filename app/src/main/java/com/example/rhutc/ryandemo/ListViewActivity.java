package com.example.rhutc.ryandemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhutc.ryandemo.adapter.ListViewAdapter;
import com.example.rhutc.ryandemo.adapter.ViewPagerAdapter;
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

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        createFakeResult();
        initialView();
    }

    private void createFakeResult() {
        result.add("My name is Ryan.");
        result.add("ライアンといます。");
        result.add("This is some more words.");
        result.add("And some more.");
        result.add("Soon the words will stop.");
        result.add("But not before");
        result.add("We have enough words");
        result.add("To give the ListView");
        result.add("Some room to scroll with.");
    }

    private void initialView() {
        View view = getLayoutInflater().inflate(R.layout.list_view_header, null);
        ViewPager vp = (ViewPager)view.findViewById(R.id.view_pager_2);

        ArrayList<Fragment> f = new ArrayList<>();
        f.add(new Fragment1());
        f.add(new Fragment2());
        f.add(new Fragment3());
        f.add(new Fragment4());
        f.add(new Fragment5());
        f.add(new Fragment6());
        f.add(new Fragment7());
        f.add(new Fragment8());
        ViewPagerAdapter vpa = new ViewPagerAdapter(this.getSupportFragmentManager());
        vpa.setFragments(f);
        vp.setAdapter(vpa);

        LinearLayout lvh = (LinearLayout) view.findViewById(R.id.list_view_header);

        ListView listView = (ListView) findViewById(R.id.list_view);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, result);
        listView.addHeaderView(lvh);

        TextView tv = new TextView(this);
        tv.setText("We have no more content.");
        tv.setTextSize(28);
        tv.setGravity(Gravity.CENTER);
        listView.addFooterView(tv);

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "listView was clicked at position: " + position, Toast.LENGTH_LONG).show();
        UtilLog.logD("testListViewActivity", String.valueOf(position));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
