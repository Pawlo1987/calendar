package com.example.user.calendarexemp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


//Адаптер для описания месяца по дням
public class MonthAdapter extends BaseAdapter {
    private Context context;
    String[] data = new String[42];
    String[] data1 = new String[42];
    String[] data2 = new String[42];

    public MonthAdapter(Context context, String[] data, String[] data1, String[] data2) {
        this.context = context;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridItem;
        gridItem = new View(context);
        //LayoutInflater inflater = getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        gridItem = inflater.inflate(R.layout.day, parent, false);
        TextView textView = (TextView) gridItem.findViewById(R.id.textView);
        TextView textView2 = (TextView) gridItem.findViewById(R.id.textView2);
        TextView textView3 = (TextView) gridItem.findViewById(R.id.textView3);
        textView.setText(data[position]);
        textView2.setText(data1[position]);
        textView3.setText(data2[position]);

        return gridItem;
    }
}
