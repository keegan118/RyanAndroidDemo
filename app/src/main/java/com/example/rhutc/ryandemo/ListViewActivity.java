package com.example.rhutc.ryandemo;

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
        result.add("A");
        result.add("B");
        result.add("C");
        result.add("D");
        result.add("E");
        result.add("F");
        result.add("G");
        result.add("H");
        result.add("I");
        result.add("J");
        result.add("K");
        result.add("L");
        result.add("M");
        result.add("N");
        result.add("O");
        result.add("P");
    }

    private void initialView() {
        listView = (ListView) findViewById(R.id.list_view);
        View v = getLayoutInflater().inflate(R.layout.list_view_header, null);

        LinearLayout lvh = (LinearLayout) v.findViewById(R.id.list_view_header);
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
}
