package com.example.kt_nhom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private List<SanPham> sanPhamList;

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int i) {
        return sanPhamList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SanPham sp = sanPhamList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_chitiet, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imgSanPham);
        TextView ten = convertView.findViewById(R.id.txtTen);
        TextView gia = convertView.findViewById(R.id.txtGia);
        TextView mota = convertView.findViewById(R.id.moTaSanPham);

        img.setImageResource(sp.getImageRes());
        ten.setText(sp.getTen());
        gia.setText(sp.getGia());
        mota.setText(sp.getMota());

        return convertView;
    }
}
