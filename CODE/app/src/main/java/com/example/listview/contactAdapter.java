package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class contactAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<contactModel> arrayContact;
    public contactAdapter(Context context, ArrayList<contactModel> array) {
        this.context = context;
        this.arrayContact = array;
    }

    @Override
    public int getCount() {
        if (arrayContact != null) {
            return arrayContact.size();
        }else
            return 0;
    }

    @Override
    public Object getItem(int position){
        return arrayContact.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_layout, parent, false);

        ImageView img = (ImageView) rowView.findViewById(R.id.imageView);
        TextView lblName = (TextView) rowView.findViewById(R.id.txt_name);
        TextView lblPhone = (TextView) rowView.findViewById(R.id.txt_phone);

        contactModel model = arrayContact.get(position);
        img.setImageResource(model.getImage());
        lblName.setText(model.getName());
        lblPhone.setText(model.getPhone());

        return rowView;
    }

    }