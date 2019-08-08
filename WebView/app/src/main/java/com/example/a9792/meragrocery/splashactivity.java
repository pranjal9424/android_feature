package com.example.a9792.meragrocery;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class splashactivity extends AppCompatActivity {

    public static int Splash=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(splashactivity.this,MainActivity.class);
                startActivity(i);
            }
        },Splash);

    }
}
