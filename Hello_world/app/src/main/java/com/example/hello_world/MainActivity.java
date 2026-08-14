package com.example.hello_world;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    Button btnShow;
    TextView txtMessage;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btnShow);
        txtMessage = findViewById(R.id.TxtMessage);

        btnShow.setOnClickListener(view -> {
            //  txtMessage.setText("Welcome to Mobile Computing Lab");
            txtMessage.setText(R.string.Welcome_message);
            //txtMessage.setTextColor(Color.BLUE);
            txtMessage.setTextColor(ContextCompat.getColor(this, R.color.black));
        });
    }
}