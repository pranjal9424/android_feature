package com.example.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    AdView mAdview;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,intiatstition.class);
                startActivity(intent);
            }
        });


        MobileAds.initialize(this,"ca-app-pub-3870552280259267~1301218668");
        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().addTestDevice("3B1D1C41DFB56A8EF0C4254E2420EA42").build();
        mAdview.loadAd(adRequest);
    }
}
