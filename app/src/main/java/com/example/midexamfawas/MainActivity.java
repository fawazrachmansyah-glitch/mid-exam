package com.example.midexamfawas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView;
    EditText editTextName, editTextStudentID, editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextStudentID = findViewById(R.id.editTextStudentID);
        editTextAge = findViewById(R.id.editTextAge);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String studentId = editTextStudentID.getText().toString().trim();
                String ageStr = editTextAge.getText().toString().trim();

                if (name.isEmpty() || studentId.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageStr);
                if (age < 17 || age > 50) {
                    Toast.makeText(MainActivity.this, "Age must be between 17 and 50", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbHelper = new DBHelper(MainActivity.this);
                StudentModel studentModel = new StudentModel(name, studentId, age);
                dbHelper.addStudent(studentModel);
                Toast.makeText(MainActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}