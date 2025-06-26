package com.example.kt_nhom1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietActivity extends AppCompatActivity {

    TextView ten, gia, mota;
    ImageView img;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);

        ten = findViewById(R.id.txtTen);
        gia = findViewById(R.id.txtGia);
        img = findViewById(R.id.imgSanPham);
        mota = findViewById(R.id.moTaSanPham);
        btnThem = findViewById(R.id.button);

        String tenSP = getIntent().getStringExtra("ten");
        String giaSP = getIntent().getStringExtra("gia");
        int imgSP = getIntent().getIntExtra("img", R.drawable.ao1);
        String motaSp = getIntent().getStringExtra("mota");

        ten.setText(tenSP);
        gia.setText(giaSP);
        img.setImageResource(imgSP);
        mota.setText(motaSp);

        btnThem.setOnClickListener(v ->
                Toast.makeText(this, "SẢN PHẨM ĐÃ ĐƯỢC THÊM VÀO GIỎ HÀNG CỦA BẠN", Toast.LENGTH_SHORT).show());
    }
}
