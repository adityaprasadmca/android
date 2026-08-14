package com.example.studentreg;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtMobile;
    RadioGroup radioGender;
    CheckBox checkAndroid, checkJava;
    Spinner spinnerCourse;
    Button btnRegister;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Connect XML components with Java
        edtName = findViewById(R.id.editTextText2);
        edtEmail = findViewById(R.id.editTextText3);
        edtMobile = findViewById(R.id.editTextText4);
        radioGender = findViewById(R.id.Gender);
        checkAndroid = findViewById(R.id.checkBox);
        checkJava = findViewById(R.id.checkBox2);
        spinnerCourse = findViewById(R.id.spinner);
        btnRegister = findViewById(R.id.button);
        txtResult = findViewById(R.id.textView3);

// Spinner data
        String[] courses = {
                "Select Course",
                "Cloud Computing AD",
                "Web Development AD",
                "Cyber Security",
                "Mobile Development"
        };
// Create Adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        courses
                );
// Attach Adapter to Spinner
        spinnerCourse.setAdapter(adapter);
// Register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Get name, email and mobile
                String name =
                        edtName.getText().toString();
                String email =
                        edtEmail.getText().toString();

                String mobile =
                        edtMobile.getText().toString();
// Get selected gender
                int selectedId =
                        radioGender.getCheckedRadioButtonId();
                String gender = "";
                if (selectedId != -1) {
                    RadioButton selectedRadio =
                            findViewById(selectedId);

                    gender =
                            selectedRadio.getText().toString();
                }
// Get selected skills
                String skills = "";
                if (checkAndroid.isChecked()) {
                    skills += "Android ";
                }
                if (checkJava.isChecked()) {
                    skills += "Java ";
                }
// Get selected course
                String course =
                        spinnerCourse
                                .getSelectedItem()

                                .toString();
// Create result
                String result =
                        "Registration Successful\n\n" +
                                "Name: " + name + "\n" +
                                "Email: " + email + "\n" +
                                "Mobile: " + mobile + "\n" +
                                "Gender: " + gender + "\n" +
                                "Skills: " + skills + "\n" +
                                "Course: " + course;

// Display result
                txtResult.setText(result);
            }
        });
    }
}