package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLog = findViewById(R.id.txtLog);

        appendLog("onCreate() Aditya72");
    }

    private void appendLog(String message) {
        txtLog.append(message + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        appendLog("onStart()  Aditya72");

    }



    @Override
    protected void onResume() {
        super.onResume();
        appendLog("onResume()  Aditya72");
    }

    @Override
    protected void onPause() {
        super.onPause();
        appendLog("onPause()  Aditya72");
    }

    @Override
    protected void onStop() {
        super.onStop();
        appendLog("onStop()  Aditya72");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appendLog("onRestart()  Aditya72");
    }

    @Override
    protected void onDestroy() {
        appendLog("onDestroy()  Aditya72");
        super.onDestroy();
    }
}