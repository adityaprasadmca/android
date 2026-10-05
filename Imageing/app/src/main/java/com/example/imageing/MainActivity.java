package com.example.imageing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import android.graphics.drawable.RotateDrawable;
import android.view.animation.RotateAnimation;
import android.widget.*;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button Rotate,Fade,Move,Blink;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        Rotate = findViewById(R.id.Rotate);
        image= findViewById(R.id.image);
        Rotate.setOnClickListener(view -> {
        });
    }
        public void Move(View view){
            startAnimation(R.anim.move);
        }
    public void Rotate(View view){
        startAnimation(R.anim.rotate);
    }
    public void Fade(View view){
        startAnimation(R.anim.fade_in
        );
    }
    public void Blink(View view){
        startAnimation(R.anim.blink);
    }

}