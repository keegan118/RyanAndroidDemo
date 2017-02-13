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

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> listResult;

    public ListViewAdapter(Context context, ArrayList<String> listResult) {
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

//        TextView view = new TextView(context);
//        view.setText(String.valueOf(position));
//        return view;

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.list_view_tv1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.list_view_tv2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.list_view_tv3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView1.setText(String.valueOf(position));
        holder.textView2.setText(listResult.get(position));
        holder.textView3.setText(String.valueOf(position));
        if (position % 2 == 0) {
            holder.textView1.setVisibility(View.VISIBLE);
            holder.textView2.setBackgroundResource(R.drawable.chat_from_bg);
            holder.lp.setMargins(UtilDensity.dp2px(mContext,50),0,0,0);
            holder.lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.textView3.setVisibility(View.INVISIBLE);
            holder.textView2.setLayoutParams(holder.lp);
        } else {
            holder.textView1.setVisibility(View.INVISIBLE);
            holder.textView2.setBackgroundResource(R.drawable.chat_to_bg);
            holder.lp.setMargins(0,0,UtilDensity.dp2px(mContext,50),0);
            holder.lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.textView3.setVisibility(View.VISIBLE);
            holder.textView2.setLayoutParams(holder.lp);
        }

//        View rowView = mInflater.inflate(R.layout.list_item, parent, false);
//        TextView textView = (TextView) rowView.findViewById(R.id.list_view_tv);
//        textView.setText(String.valueOf(position));

//        textView.setOnClickListener(new View.OnClickListener(){
//            @Override
//                    public void onClick(View v){
//
//            }
//        });

        return convertView;
    }
}

class ViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
}