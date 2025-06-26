package com.example.mysqlite;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mysqlite.adapter.EmployeeAdapter;
import com.example.mysqlite.model.Employee;
import com.example.mysqlite.sqlite.DBHelper;
import com.example.mysqlite.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EmployeeAdapter employeeAdapter;
private ListView lvEmployees;
private String employeeId;
private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        DBHelper dpHelper = new DBHelper(this);
//        SQLiteDatabase database = dpHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        lvEmployees = findViewById(R.id.listView);
        EmployeeDao dao = new EmployeeDao(this);
        list = dao.getAll();

        employeeAdapter = new EmployeeAdapter(this, list);
        lvEmployees. setAdapter(employeeAdapter);
        lvEmployees. setOnItemClickListener(new AdapterView. OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long L) {
                Employee emp = list.get(i);
                employeeId = emp.getId();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        EmployeeDao dao = new EmployeeDao(this);
        List<Employee> updatedList = dao.getAll();

        list.clear();
        list.addAll(updatedList);
        employeeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick (View view){
        int id = view.getId();
        if (id == R.id.btnEdit) {
            Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnInsert) {
            Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
            startActivity(intent);

        } else if (id == R.id.btnDelete) {
            if (employeeId == null) {
                Toast.makeText(this,"Employee id must be selected",  Toast.LENGTH_SHORT).show();
                return;
            }
            EmployeeDao dao = new EmployeeDao(this);
            dao.delete(employeeId);
            employeeId = null;
            onResume();
            Toast.makeText(this, "Employee was deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
