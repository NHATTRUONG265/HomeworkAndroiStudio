package com.example.listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ArrayList<contactModel> arraycontact = new ArrayList<>();
private ListView listContact;
//KHỞI TẠO 1 ARRAYLIST ĐỂ CHỮA CONTACT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khoiTaoData();
        listContact = (ListView) findViewById(R.id.list_contact);
        contactAdapter adapter = new contactAdapter(this, arraycontact);

        listContact.setAdapter(adapter);
}
    private void khoiTaoData(){
        arraycontact.add(new contactModel(  "Aang", "0900000001", R.drawable.user1));
        arraycontact.add(new contactModel(  "Katara", "0900000002", R.drawable.user2));
        arraycontact.add(new contactModel(  "Kyoshi", "0900000003", R.drawable.user3));
        arraycontact.add(new contactModel(  "Sokka",  "0900000004", R.drawable.user4));
        arraycontact.add(new contactModel(  "Suki",  "0900000005", R.drawable.user5));
        arraycontact.add(new contactModel(  "Toph",  "0900000006",R.drawable.user6));
        arraycontact.add(new contactModel(  "TyLee", "0900000007", R.drawable.user7));
        arraycontact.add(new contactModel(  "Zuko",  "0900000008",R.drawable.user8));
}
}