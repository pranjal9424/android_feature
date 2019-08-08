package com.example.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class intiatstition extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intiatstition);

        ////bannerAd
        mAdview=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        MobileAds.initialize(this, "ca-app-pub-3870552280259267~1301218668");
        prePaperAd();

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                        }
                        else {
                            Log.d("Tag","Interstitals Ad not Loaded");
                        }
                    }
                });
            }
        },10,10, SECONDS);


    }


    public  void prePaperAd(){
        mInterstitialAd =new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3870552280259267/9500941181");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("3B1D1C41DFB56A8EF0C4254E2420EA42").build());
    }


}
