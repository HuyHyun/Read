package com.example.read.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.read.R;
import com.example.read.Services.TinTuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customadapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<TinTuc> tinTucList;
    private ArrayList<TinTuc> arrayList;


    public Customadapter(Context context, int layout, List<TinTuc> tinTucList) {
        this.context = context;
        this.layout = layout;
        this.tinTucList = tinTucList;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(tinTucList);

    }

    @Override
    public int getCount() {
        return tinTucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class ViewHolder {
        TextView txt;
        ImageView img;
        TextView date;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            //ánh xạ
            viewHolder.txt = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgHinh);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TinTuc tinTuc = tinTucList.get(position);
        viewHolder.txt.setText(tinTuc.tieuDe);
        String[] sdate = tinTuc.getDate().split("\\+");
        viewHolder.date.setText(sdate[0]);
        Picasso.get().load(tinTuc.hinhAnh).into(viewHolder.img);
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        tinTucList.clear();
        if (charText.length() == 0) {
            tinTucList.addAll(arrayList);
        } else {
            for (TinTuc tinTuc : arrayList) {
                if (tinTuc.getTieuDe().toLowerCase(Locale.getDefault()).contains(charText)) {
                    tinTucList.add(tinTuc);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void refresh(ArrayList<TinTuc> tinTucArrayList) {
        this.arrayList = tinTucArrayList;
        notifyDataSetChanged();

    }


}