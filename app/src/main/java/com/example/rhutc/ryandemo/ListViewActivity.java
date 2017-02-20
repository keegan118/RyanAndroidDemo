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
import com.example.rhutc.ryandemo.fragment.ContentFragment;
import com.example.rhutc.ryandemo.fragment.HistoryFragment;
import com.example.rhutc.ryandemo.fragment.LoginFragment;
import com.example.rhutc.ryandemo.util.UtilLog;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        result = new ArrayList<>();
        createFakeResult();
        initialView();
    }

    private void createFakeResult() {
        result.add("My name is Ryan.");
        result.add("ライアンといます。");
        result.add("C");
        result.add("D");
    }

    private void initialView() {
        listView = (ListView) findViewById(R.id.list_view);
        View view = getLayoutInflater().inflate(R.layout.list_view_header, null);

        ViewPager vp = (ViewPager)view.findViewById(R.id.view_pager_2);
        ArrayList<Fragment> f = new ArrayList<>();
        f.add(new LoginFragment());
        f.add(new ContentFragment());
        f.add(new HistoryFragment());
        ViewPagerAdapter vpa = new ViewPagerAdapter(this.getSupportFragmentManager());
        vpa.setFragments(f);
        vp.setAdapter(vpa);

        LinearLayout lvh = (LinearLayout) view.findViewById(R.id.list_view_header);

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
