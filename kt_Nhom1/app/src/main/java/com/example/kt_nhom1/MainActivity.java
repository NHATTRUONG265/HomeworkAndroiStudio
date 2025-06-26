package com.example.kt_nhom1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<SanPham> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvSanPham);
        ds = new ArrayList<>();
        ds.add(new SanPham("Sản phẩm A", "100.000đ", R.drawable.ao1, "Ao duoc lam bang vai"));

        SanPhamAdapter adapter = new SanPhamAdapter(this, ds);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            SanPham sp = ds.get(i);
            Intent intent = new Intent(MainActivity.this, ChiTietActivity.class);
            intent.putExtra("ten", sp.getTen());
            intent.putExtra("gia", sp.getGia());
            intent.putExtra("img", sp.getImageRes());
            intent.putExtra("mota", sp.getMota());
            startActivity(intent);
        });
    }


}
