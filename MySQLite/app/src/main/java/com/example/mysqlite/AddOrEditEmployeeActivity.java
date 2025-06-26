package com.example.mysqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysqlite.model.Employee;
import com.example.mysqlite.sqlite.EmployeeDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmployeeId, etName, etSalary;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        etEmployeeId = findViewById(R.id.etEmployeeId);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);

        btnSave = findViewById(R.id.btSave);
        btnSave.setOnClickListener(this);

        findViewById(R.id.btListEmployee).setOnClickListener(this);

        readEmployee();
    }

    private void readEmployee() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle == null) {
            return;
        }

        String id = bundle.getString("id");
        EmployeeDao dao = new EmployeeDao(this);
        Employee emp = dao.getById(id);

        etEmployeeId.setText(emp.getId());
        etName.setText(emp.getName());
        etSalary.setText(String.valueOf(emp.getSalary()));
        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btSave) {
            String idText = etEmployeeId.getText().toString().trim();
            String nameText = etName.getText().toString().trim();
            String salaryText = etSalary.getText().toString().trim();

            if (idText.isEmpty() || nameText.isEmpty() || salaryText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            EmployeeDao dao = new EmployeeDao(this);
            Employee emp = new Employee();

            emp.setId(idText);
            emp.setName(nameText);
            emp.setSalary(Float.parseFloat(salaryText));

            if (btnSave.getText().toString().equalsIgnoreCase("Save")) {
                dao.insert(emp);
                Toast.makeText(this, "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
            } else {
                dao.update(emp);
                Toast.makeText(this, "Cập nhật nhân viên thành công!", Toast.LENGTH_SHORT).show();
            }

            finish(); // quay về màn chính
        }
    }
}
