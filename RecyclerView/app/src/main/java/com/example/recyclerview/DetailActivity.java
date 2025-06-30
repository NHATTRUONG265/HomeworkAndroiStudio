package com.example.recyclerview;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget. ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx. appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

//    ImageView detailImageView;
//    TextView detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailTextView = findViewById(R.id.detailTextView);

        Food food = getIntent().getParcelableExtra("foodItem");

        if (food != null) {
            detailImageView.setImageResource(food.getImageResId());
            String detailText = "Tên món ăn: " + food.getName() +
                    "\nMô tả: " + food.getDescription() +
                    "\nGiá: " + food.getPrice() + " VND";
            detailTextView.setText(detailText);
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v ->

        {
            // Gọi hiệu ứng finish và override animation
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });

        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(v -> {
            Toast.makeText(this, "Bạn đã gọi món: " + food.getName(), Toast.LENGTH_SHORT).show();

            // Lưu món đã đặt
            SharedPreferences orderedPrefs = getSharedPreferences("OrderedFood", MODE_PRIVATE);
            SharedPreferences.Editor orderedEditor = orderedPrefs.edit();
            orderedEditor.putString("orderedFoodName", food.getName());
            orderedEditor.apply();

            // Lưu món vừa xem
            SharedPreferences viewedPrefs = getSharedPreferences("LastViewedFood", MODE_PRIVATE);
            SharedPreferences.Editor viewedEditor = viewedPrefs.edit();
            viewedEditor.putString("lastFoodName", food.getName());
            viewedEditor.apply();
        });

        Button callButton = findViewById(R.id.callButton);
        Button mapButton = findViewById(R.id.mapButton);
        Button webButton = findViewById(R.id.webButton);

        callButton.setOnClickListener(v -> {
            if (food != null && food.getPhone() != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + food.getPhone()));
                startActivity(intent);
            }
        });

        mapButton.setOnClickListener(v -> {
            if (food != null && food.getAddress() != null) {
                // Dùng Uri geo với truy vấn địa chỉ
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(food.getAddress()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        webButton.setOnClickListener(v -> {
            if (food != null && food.getWebsite() != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(food.getWebsite()));
                startActivity(intent);
            }
        });
    }
}

