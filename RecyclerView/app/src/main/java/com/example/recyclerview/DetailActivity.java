package com.example.recyclerview;
import android.content.SharedPreferences;
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

    }
}

