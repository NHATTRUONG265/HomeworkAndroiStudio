package com.example.recyclerview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        foodList = new ArrayList<>();
        foodList.add(new Food("Phở", R.drawable.pho, "Phở bò truyền thống với nước dùng đậm đà.", 45000, "0123456789", "21 Lê Lợi, Hà Nội", "https://pho.vn"));
        foodList.add(new Food("Bún chả", R.drawable.buncha, "Bún chả Hà Nội thơm ngon, thịt nướng vàng ươm.", 40000, "0987654321", "1 Tràng Tiền, Hà Nội", "https://buncha.vn"));
        foodList.add(new Food("Bánh mì", R.drawable.banhmi, "Bánh mì kẹp thịt, rau sống, nước sốt.", 20000, "0909090909", "5 Nguyễn Huệ, TP.HCM", "https://banhmi.vn"));
        foodList.add(new Food("Cơm tấm", R.drawable.comtam, "Cơm tấm sườn bì chả, trứng ốp la.", 50000, "0911222333", "10 Lý Tự Trọng, TP.HCM", "https://comtam.vn"));
        foodList.add(new Food("Gỏi cuốn", R.drawable.goicuon, "Gỏi cuốn tôm thịt, nước chấm đậm đà.", 30000, "0933444555", "15 Pasteur, TP.HCM", "https://goicuon.vn"));
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);
        // Swipe to delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false; // Không xử lý kéo thả
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                foodAdapter.removeItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("LastViewedFood", MODE_PRIVATE);
        String lastFood = prefs.getString("lastFoodName", "Bạn chưa xem món ăn nào");

        TextView lastViewedTextView = findViewById(R.id.lastViewedTextView);
        lastViewedTextView.setText("Bạn vừa xem: " + lastFood);

        SharedPreferences prefsOrder = getSharedPreferences("OrderedFood", MODE_PRIVATE);
        String orderedFood = prefsOrder.getString("orderedFoodName", null);

        if (orderedFood != null) {
            Toast.makeText(this, "Bạn đã gọi mon: " + orderedFood, Toast.LENGTH_LONG).show();
        }
    }
}