package com.example.preferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText edtName;
    Button btnSave,btnDisplay;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);
        btnDisplay=findViewById(R.id.btnDisplay);
        txtResult = findViewById(R.id.txtResult);


        btnSave.setOnClickListener(v -> {
            String name= edtName.getText().toString();
            getSharedPreferences("StudentData",MODE_PRIVATE)
                    .edit()
                    .putString("student_name",name)
                    .apply();

            txtResult.setText("Data saved");
        });

        btnDisplay.setOnClickListener(v -> {
            String name = getSharedPreferences(
                    "StudentData",
                    MODE_PRIVATE
            ).getString(
                    "student_name",
        "No Fata Found"
            );
            txtResult.setText("Student name:"+ name);
        });
    }
}