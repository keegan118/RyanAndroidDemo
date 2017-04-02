package com.example.rhutc.ryandemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhutc.ryandemo.R;
import com.example.rhutc.ryandemo.util.UtilDensity;

import java.util.ArrayList;

public class NavDrawerAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> listResult;

    public NavDrawerAdapter(Context context, ArrayList<String> listResult) {
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listResult = listResult;
    }

    @Override
    public int getCount() {
        return listResult.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.nav_item, parent, false);
            holder = new NavHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.nav_item_tv1);
            convertView.setTag(holder);
        } else {
            holder = (NavHolder) convertView.getTag();
        }

        holder.textView1.setText(listResult.get(position));

        return convertView;
    }
}

class NavHolder {
    TextView textView1;
    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
}