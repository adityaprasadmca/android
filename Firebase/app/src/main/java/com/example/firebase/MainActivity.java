package com.example.firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtCourse;
    private Button btnInsert, btnView, btnUpdate, btnDelete;
    private TextView txtResult;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI Elements
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtCourse = findViewById(R.id.edtCourse);
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        txtResult = findViewById(R.id.txtResult);

        // Firebase Node Reference
        mDatabase = FirebaseDatabase.getInstance().getReference("Students");

        // INSERT RECORD
        btnInsert.setOnClickListener(v -> {
            String id = edtId.getText().toString().trim();
            String name = edtName.getText().toString().trim();
            String course = edtCourse.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(id, name, course);
            mDatabase.child(id).setValue(student)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Inserted successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        // VIEW ALL RECORDS
        btnView.setOnClickListener(v -> {
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        txtResult.setText("No records found.");
                        return;
                    }

                    StringBuilder builder = new StringBuilder();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Student s = ds.getValue(Student.class);
                        if (s != null) {
                            builder.append("ID: ").append(s.id)
                                    .append("\nName: ").append(s.name)
                                    .append("\nCourse: ").append(s.course);

                        }
                    }
                    txtResult.setText(builder.toString());
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    txtResult.setText("Failed to read data: " + error.getMessage());
                }
            });
        });

        // UPDATE RECORD
        btnUpdate.setOnClickListener(v -> {
            String id = edtId.getText().toString().trim();
            String name = edtName.getText().toString().trim();
            String course = edtCourse.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {
                Toast.makeText(this, "ID, Name, and Course are required to update", Toast.LENGTH_SHORT).show();
                return;
            }

            mDatabase.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Student updatedStudent = new Student(id, name, course);
                        mDatabase.child(id).setValue(updatedStudent);
                        Toast.makeText(MainActivity.this, "Record updated!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Student ID does not exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // DELETE RECORD
        btnDelete.setOnClickListener(v -> {
            String id = edtId.getText().toString().trim();

            if (id.isEmpty()) {
                edtId.setError("Enter ID to delete");
                return;
            }

            mDatabase.child(id).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Record deleted successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Delete failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    private void clearFields() {
        edtId.setText("");
        edtName.setText("");
        edtCourse.setText("");
    }
}