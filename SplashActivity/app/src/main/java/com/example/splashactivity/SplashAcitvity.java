package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitvity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new  Intent(SplashAcitvity.this,MainActivity.class);
                startActivity(i);
            }
        },2000);
    }
}
