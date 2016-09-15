package com.andromed.codes.universalresourcedownloader.demo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.andromed.codes.universalresourcedownloader.R;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 11/09/2016.
 */
public class ListViewAdapter extends BaseAdapter {

    static class Holder {
        ImageView imageView;
    }

    Context mContext;
    ArrayList<Bitmap> items;

    public ListViewAdapter(Context context, ArrayList<Bitmap> items) {
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = (LayoutInflater.from(mContext)).inflate(R.layout.listview_item, parent, false);
            holder = new Holder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.imageView.setImageBitmap(items.get(position));

        return convertView;
    }
}
